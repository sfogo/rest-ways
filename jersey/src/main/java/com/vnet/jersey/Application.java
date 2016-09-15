package com.vnet.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        // This will pick LoincResource
        // from restways base module.
        packages("com.vnet.resource");
    }
}
