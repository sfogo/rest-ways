package com.vnet.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class ResourceTest {

    private HttpServer server;
    private URI uri = Main.uri(8080);

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.createHttpServer(uri);
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testGet() throws Exception {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target(uri.toString());
        final String response = target.path("/codes/11113-8").request().get(String.class);
        final String pattern = "{\"loincNum\":\"11113-8\"";
        assertEquals(response.substring(0,pattern.length()), pattern);
    }
}
