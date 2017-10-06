package com.example.kamioka.api2;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api2")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        this.register(Books.class);
    }
}
