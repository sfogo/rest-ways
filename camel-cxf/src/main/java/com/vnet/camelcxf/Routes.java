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

import com.vnet.camelcxf.resources.LoincServiceImpl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Routes extends RouteBuilder {

    private static final String REST_ENDPOINT_URI = "cxfrs://http://localhost:{{restEndpointPort}}/loinc"
        + "?resourceClasses=com.vnet.camelcxf.resources.LoincServiceImpl";
    
    public void configure() {
        errorHandler(noErrorHandler());
        from(REST_ENDPOINT_URI).process(new MappingProcessor(new LoincServiceImpl()));
    }
    
    // Mapping the request to object's invocation
    private static class MappingProcessor implements Processor {

        final private Log logger = LogFactory.getLog(Routes.class);

        private Class<?> beanClass;
        private Object instance;
        
        public MappingProcessor(Object obj) {
            beanClass = obj.getClass();
            instance = obj;
        }
         
        public void process(Exchange exchange) throws Exception {
            String operationName = exchange.getIn().getHeader(CxfConstants.OPERATION_NAME, String.class);
            logger.info(operationName);
            Method method = findMethod(operationName, exchange.getIn().getBody(Object[].class));
            try {
                Object response = method.invoke(instance, exchange.getIn().getBody(Object[].class));
                exchange.getOut().setBody(response);
            }  catch (InvocationTargetException e) {
                throw (Exception)e.getCause();
            }
        }
        
        private Method findMethod(String operationName, Object[] parameters) throws SecurityException, NoSuchMethodException {            
            return beanClass.getMethod(operationName, getParameterTypes(parameters));
        }
        
        private Class<?>[] getParameterTypes(Object[] parameters) {
            if (parameters == null) {
                return new Class[0];
            }
            Class<?>[] answer = new Class[parameters.length];
            int i = 0;
            for (Object object : parameters) {
                answer[i] = object.getClass();
                i++;
            }
            return answer;
        }
    }

}
