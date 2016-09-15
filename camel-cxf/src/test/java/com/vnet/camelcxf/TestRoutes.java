package com.vnet.camelcxf;

import com.vnet.resource.LoincResource;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class TestRoutes extends CamelTestSupport {

    private final String mockLoinc = "mock:loinc";

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        System.setProperty("restEndpointPort", "8080");
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(Routes.REST_ENDPOINT_URI)
                    .process(new MappingProcessor(new LoincResource()))
                    .marshal().json(JsonLibrary.Jackson)
                    .to(mockLoinc);
            }
        };
    }

    @Test
    public void test01() throws Exception {
        final String code = "56789-1";
        final String jsonStart = "{\"loincNum\":\"" + code + "\"";
        final String url = "http://localhost:8080/loinc/codes/" + code;
        final MockEndpoint mock = getMockEndpoint(mockLoinc);
        mock.whenAnyExchangeReceived(exchange -> {
            final String body = exchange.getIn().getBody(String.class);
            if (body.startsWith(jsonStart))
                exchange.getIn().setBody("OK");
            else
                exchange.getIn().setBody("ERROR");
        });
        String body = template.requestBody(url, null, String.class);
        assertEquals("OK", body);
        assertMockEndpointsSatisfied();
    }
}
