package com.example.ivocosta.restmocker;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

/**
 * Created by ivo.costa@estafet.com on 09/02/2017.
 */
public class RestMocker {
    public static void main(String args[]) throws Exception{
        Server server = new Server(9999);

        ResourceConfig config = new ResourceConfig();

        Resource.Builder resourceBuilder = Resource.builder("rest");
        resourceBuilder.addChildResource("test").addMethod("get").handledBy(new Inflector<ContainerRequestContext, Response>() {
            public Response apply(ContainerRequestContext containerRequestContext) {
                return Response.ok("I'm here").build();
            }
        });

        config.registerResources(resourceBuilder.build());

        ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));

        ServletContextHandler contextHandler = new ServletContextHandler(server, "/*");
        contextHandler.addServlet(servletHolder, "/*");


        try {
            server.start();
            server.join();
        } finally {
            server.stop();
        }

    }
}
