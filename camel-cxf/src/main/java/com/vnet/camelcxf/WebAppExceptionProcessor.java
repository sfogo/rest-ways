package com.vnet.camelcxf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.WebApplicationException;

class WebAppExceptionProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        final WebApplicationException e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, WebApplicationException.class);
        exchange.getIn().setBody(new ObjectMapper().writeValueAsString(e.getResponse().getEntity()));
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE,e.getResponse().getStatus());
    }
}