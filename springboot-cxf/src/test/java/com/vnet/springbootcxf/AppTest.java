package com.vnet.springbootcxf;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)

@ContextConfiguration(classes = Application.class)
public class AppTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCode() {
        String body = restTemplate.getForObject("/loinc/codes/18888-8", String.class);
        assertThat(body).contains("\"loincNum\":\"18888-8\"");
    }

    @Test
    public void testError() {
        String body = restTemplate.getForObject("/loinc/codes", String.class);
        assertEquals("{\"code\":102,\"message\":\"Missing query parameter [q]\"}", body);
    }
}
