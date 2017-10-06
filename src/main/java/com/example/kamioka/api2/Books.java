package com.example.kamioka.api2;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/books")
public class Books {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map get() {
        return ImmutableMap.builder()
                .put("buildBy", "jersey")
                .put("books", ImmutableList.of("a", "b", "c")).build();
    }
}
