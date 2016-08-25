package com.vnet.service;

import com.vnet.db.SqlService;
import com.vnet.model.Measurement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MeasurementTest {

    private MeasurementService service;

    @Before
    public void setUp() throws Exception {
        service = new MeasurementService();
        final SqlService sqlService = new SqlService();
        service.setSqlService(sqlService);
        service.setLoincService(new LoincService(sqlService));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01() throws Exception {
        final String id = String.valueOf(System.currentTimeMillis());
        final String loinc = "11120-3";

        service.upload(new Measurement(id,"pid","did",loinc,"u",3.14159f,false,new Date(),new Date(),"note"));

        final Measurement measurement = service.get(id);
        assertEquals(measurement.getId(), id);
        assertEquals(measurement.getCode(), loinc);

        service.delete(id);
        try {
            service.get(id);
        } catch (ServiceException e) {
            assertEquals(e.getCode().getValue(), ServiceException.Code.NOT_FOUND.getValue());
        }
    }
}
