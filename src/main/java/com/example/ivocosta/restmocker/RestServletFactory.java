package com.example.ivocosta.restmocker;

import com.example.ivocosta.restmocker.model.Api;
import com.example.ivocosta.restmocker.model.elements.ApiResource;
import com.example.ivocosta.restmocker.model.elements.MethodDefinition;
import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Created by Ivo Costa on 10/02/2017.
 */
public class RestServletFactory implements InstanceFactory<ServletContainer> {
    private final Api definition;

    public RestServletFactory(Api api) {
        definition = api;
    }

    public InstanceHandle<ServletContainer> createInstance() throws InstantiationException {
        ResourceConfig config = new ResourceConfig();

        Resource apiResource = buildApiResources();
        config.registerResources(apiResource);

        final ServletContainer servletContainer = new ServletContainer(config);

        return new ServletContainerHandler(servletContainer);
    }

    private Resource buildApiResources() {
        Resource.Builder resourceBuilder = Resource.builder("rest");

        //TODO: use full API structure
        ApiResource apiResource = definition.getResources().get(0);

        Set<MethodDefinition> methodDefinitions = apiResource.getMethodDefinitions();

        Resource.Builder subResource = resourceBuilder.addChildResource(apiResource.getPath());

        for (MethodDefinition method : methodDefinitions) {
            final String response = method.getResponse();

            subResource.addMethod(method.getMethod().toString()).handledBy(new Inflector<ContainerRequestContext, Response>() {
                public Response apply(ContainerRequestContext containerRequestContext) {
                    return Response.ok(response).build();
                }
            });
        }

        return resourceBuilder.build();
    }
}
