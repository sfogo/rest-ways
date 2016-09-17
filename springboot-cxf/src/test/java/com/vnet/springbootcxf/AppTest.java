package com.vnet.springbootcxf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnet.model.Loinc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

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
    public void testGetCodes() throws Exception {
        String body = restTemplate.getForObject("/loinc/codes?q=8888", String.class);
        final Loinc[] codes = new ObjectMapper().readValue(body, Loinc[].class);
        assertThat(codes.length).isGreaterThan(1);
        Arrays.stream(codes).map(Loinc::getLoincNum).forEach((s) -> assertThat(s).contains("8888"));
    }

    @Test
    public void testGetCodesError() {
        String body = restTemplate.getForObject("/loinc/codes", String.class);
        assertThat(body).isEqualTo("{\"code\":102,\"message\":\"Missing query parameter [q]\"}");
    }
}
