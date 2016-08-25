package com.vnet.service;

import com.vnet.model.ErrorDetails;
import com.vnet.model.SimpleError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceExceptionTest {

    private ServiceException e;
    private ErrorDetails details;
    private SimpleError error ;

    @Before
    public void setUp() throws Exception {
        e = new ServiceException(ServiceException.Code.SQL, "a", "b", "c");
        details = e.asErrorDetails();
        error = e.asError();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01() throws Exception {
        assertEquals(details.getCode(), e.getCode().getValue());
        assertEquals(details.getMessage(), e.getMessage());
        assertEquals(details.getAddlData().getClass(), String[].class);
        assertEquals(500, e.getCode().getStatus());
        assertEquals(e.getCode().getValue(), error.getCode());

        final String[] data = (String[]) details.getAddlData();
        assertEquals("a", data[0]);
        assertEquals("b", data[1]);
        assertEquals("c", data[2]);

        assertEquals(true, error.getMessage().contains("[a]"));
        assertEquals(true, error.getMessage().contains("[b]"));
        assertEquals(true, error.getMessage().contains("[c]"));
    }

    @Test
    public void test02() throws Exception {
        assertEquals(true, null == new ServiceException(ServiceException.Code.ILV).getData());
    }
}
