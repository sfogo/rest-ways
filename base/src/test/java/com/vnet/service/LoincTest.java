package com.vnet.service;

import com.vnet.db.SqlService;
import com.vnet.model.Loinc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class LoincTest {

    private LoincService service1;
    private LoincService service2;

    @Before
    public void setUp() throws Exception {
        final SqlService sqlService = new SqlService();
        service1 = new LoincService(sqlService);
        service2 = new LoincService();
        service2.setSqlService(sqlService);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetCode01() throws Exception {
        final String num = "11113-8";
        final Loinc code = service1.getCode(num);
        assertEquals(num, code.getLoincNum());
    }

    @Test
    public void testGetCode02() throws Exception {
        final String num = "12346-3";
        final Loinc code = service2.getCode(num);
        assertEquals(num, code.getLoincNum());
    }

    @Test
    public void testGetCodes() throws Exception {
        final Collection<Loinc> codes = service1.getCodes("1234",null);
        assertEquals(true, codes.size() > 1);
    }
}
