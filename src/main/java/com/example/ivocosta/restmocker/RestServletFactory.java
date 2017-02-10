package com.example.ivocosta.restmocker;

import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

/**
 * Created by Ivo Costa on 10/02/2017.
 */
public class RestServletFactory implements InstanceFactory<ServletContainer> {
    public InstanceHandle<ServletContainer> createInstance() throws InstantiationException {
        ResourceConfig config = new ResourceConfig();

        Resource.Builder resourceBuilder = Resource.builder("rest");
        resourceBuilder.addChildResource("test").addMethod("get").handledBy(new Inflector<ContainerRequestContext, Response>() {
            public Response apply(ContainerRequestContext containerRequestContext) {
                return Response.ok("I'm here").build();
            }
        });

        config.registerResources(resourceBuilder.build());

        final ServletContainer servletContainer = new ServletContainer(config);

        return new ServletContainerHandler(servletContainer);
    }
}
