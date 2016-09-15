package com.vnet.camelcxf;

import com.vnet.model.Loinc;
import com.vnet.resource.LoincResource;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;

public class TestMappingProcessor {
    private MappingProcessor processor = new MappingProcessor(new LoincResource());

    @Test
    public void testNoSuchMethod()  {
        final Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().getHeaders().put(CxfConstants.OPERATION_NAME, "foobar");
        exchange.getIn().setBody(new String[] {"param1", "param2", "param3"});
        try {
            processor.process(exchange);
        } catch (Exception e) {
            assert(e.getClass().equals(NoSuchMethodException.class));
        }
    }

    @Test
    public void testSuchMethod() throws Exception {
        final Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().getHeaders().put(CxfConstants.OPERATION_NAME, "getCode");
        exchange.getIn().setBody(new String[] {"8462-4"});
        processor.process(exchange);
        assert(exchange.getOut().getBody().getClass().equals(Loinc.class));
    }
}
