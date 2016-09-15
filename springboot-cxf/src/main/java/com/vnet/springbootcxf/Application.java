package com.vnet.springbootcxf;

import com.vnet.resource.LoincResource;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Autowired
    private Bus bus;

    static public void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Server loincServer() {
        final JAXRSServerFactoryBean endpoints = new JAXRSServerFactoryBean();
        endpoints.setBus(bus);
        endpoints.setAddress("/loinc");
        endpoints.setProviders(Arrays.<Object>asList(new MoxyJsonFeature()));
        endpoints.setServiceBeans(Arrays.<Object>asList(new LoincResource()));
        return endpoints.create();
    }
}
