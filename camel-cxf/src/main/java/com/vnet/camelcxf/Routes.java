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

import com.vnet.resource.LoincResource;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import javax.ws.rs.WebApplicationException;


class Routes extends RouteBuilder {

    public static final String REST_ENDPOINT_URI = "cxfrs://http://localhost:{{restEndpointPort}}/loinc"
        + "?resourceClasses=com.vnet.resource.LoincResource";

    public void configure() {
        onException(WebApplicationException.class)
            .handled(true)
            .process(new WebAppExceptionProcessor());

        from(REST_ENDPOINT_URI)
            .process(new MappingProcessor(new LoincResource()))
            .marshal().json(JsonLibrary.Jackson);
    }
}
