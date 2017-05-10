package com.example.ivocosta.restmocker;

import org.glassfish.jersey.process.Inflector;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

/**
 * Created by Ivo on 13/02/2017.
 */
public class RestInflector implements Inflector<ContainerRequestContext, Response> {
    private String response;

    public RestInflector(String response) {
        this.response = response;
    }

    @Override
    public Response apply(ContainerRequestContext containerRequestContext) {
        return Response.ok(response).build();
    }
}
