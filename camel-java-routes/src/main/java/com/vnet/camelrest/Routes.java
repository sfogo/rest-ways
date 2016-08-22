package com.vnet.camelrest;

import com.vnet.camelrest.model.Loinc;
import com.vnet.camelrest.model.Measurement;
import com.vnet.camelrest.service.ItemException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class Routes extends RouteBuilder {


    class ItemExceptionProcessor implements Processor {
        public void process(Exchange exchange) throws Exception {
            final ItemException e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT,ItemException.class);
            exchange.getIn().setBody(e.getJsonError());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE,e.getHttpResponseCode());
        }
    }

    @Override
    public void configure() throws Exception {

        onException(ItemException.class).handled(true).process(new ItemExceptionProcessor());

        // configure we want to use servlet as the component for the rest DSL
        // and we enable json binding mode
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .contextPath("loinc");

        rest("/measurements").description("Measurement rest service")
            .consumes("application/json").produces("application/json")

            .get("/{id}").description("Find Measurement by id").outType(Measurement.class)
            .to("bean:measurementService?method=get(${header.id})")

            .delete("/{id}").description("Delete Measurement by id").outType(Measurement.class)
            .to("bean:measurementService?method=delete(${header.id})")

            .post().description("Upload Measurement").type(Measurement.class).outType(Measurement.class)
            .to("bean:measurementService?method=upload")

            .get("/").description("Find Measurements").outTypeList(Measurement.class)
            .to("bean:measurementService?method=list(${header.last})");

        rest("/codes").description("LOINC Code rest service")
            .consumes("application/json").produces("application/json")

            .get("/{code}").description("Get LOINC Code").outType(Loinc.class)
            .to("bean:loincService?method=getCode(${header.code})")

            .get("/").description("Find LOINC Codes by type and value").outTypeList(Loinc.class)
            .to("bean:loincService?method=find(${header.q},${header.type})");
    }

}