package com.vnet.jersey;

import com.vnet.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

public class Main {
    static private final Log logger = LogFactory.getLog(Main.class);

    static URI uri(int port) {
        return URI.create(String.format("http://localhost:%d/loinc/", port));
    }

    static HttpServer createHttpServer(URI uri) {
        // create a new instance of grizzly http server (false = do not start it)
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(uri, new Application(), false);
    }

    public static void main(String[] args) {
        try {
            final int port = Utils.getenv("DEMO_REST_PORT", 8080);
            final HttpServer server = createHttpServer(uri(port));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Shutdown:" + server.toString());
                server.shutdownNow();
            }));

            server.start();
            System.out.println(uri(port) + " : Main started. Shutdown with CTRL+C");
            Thread.currentThread().join();
        } catch (IOException | InterruptedException e) {
            logger.error(e);
        }
    }
}

