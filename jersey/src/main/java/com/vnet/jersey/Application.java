package com.vnet.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        packages("com.vnet.jersey");
    }
}
