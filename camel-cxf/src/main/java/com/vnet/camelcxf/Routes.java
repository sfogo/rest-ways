/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vnet.camelcxf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnet.resource.LoincResource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.WebApplicationException;


public class Routes extends RouteBuilder {

    private static final String REST_ENDPOINT_URI = "cxfrs://http://localhost:{{restEndpointPort}}/loinc"
        + "?resourceClasses=com.vnet.resource.LoincResource";

    private class WebAppExceptionProcessor implements Processor {
        public void process(Exchange exchange) throws Exception {
            final WebApplicationException e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT,WebApplicationException.class);
            exchange.getIn().setBody(new ObjectMapper().writeValueAsString(e.getResponse().getEntity()));
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE,e.getResponse().getStatus());
        }
    }

    public void configure() {
        onException(WebApplicationException.class)
            .handled(true)
            .process(new WebAppExceptionProcessor());

        from(REST_ENDPOINT_URI)
            .process(new MappingProcessor(new LoincResource()))
            .marshal().json(JsonLibrary.Jackson);
    }
    
    /**
     * Mapping the request to object's invocation
     */
    private static class MappingProcessor implements Processor {
        final private Log logger = LogFactory.getLog(getClass());
        final private Class<?> beanClass;
        final private Object instance;
        
        MappingProcessor(Object obj) {
            this.beanClass = obj.getClass();
            this.instance = obj;
        }
         
        public void process(Exchange exchange) throws Exception {
            final String methodName = exchange.getIn().getHeader(CxfConstants.OPERATION_NAME, String.class);
            final Object[] parameters = exchange.getIn().getBody(Object[].class);
            final Method method = getMethod(methodName, parameters);
            try {
                Object response = method.invoke(instance, parameters);
                exchange.getOut().setBody(response);
            }  catch (InvocationTargetException e) {
                logger.error(e.getMessage());
                throw (Exception) e.getCause();
            }
        }

        private Method getMethod(String methodName, Object[] parameters) throws NoSuchMethodException {
            logger.info(String.format("Find Method : %s.%s", beanClass.getName(), methodName));
            if (parameters == null)
                return beanClass.getMethod(methodName);

            final Class<?>[] parameterTypes = new Class[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parameterTypes[i] = parameters[i].getClass();
                logger.info(String.format("With Parameter: %s [%s]",parameterTypes[i].getName(), parameters[i].toString()));
            }
            return beanClass.getMethod(methodName, parameterTypes);
        }
    }
}
