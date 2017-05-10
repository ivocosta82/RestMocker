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

        for(ApiResource apiResource : definition.getResources()) {
//            buildResourceTree(resourceBuilder, apiResource);
            Set<MethodDefinition> methodDefinitions = apiResource.getMethodDefinitions();
            Resource.Builder subResource = Resource.builder(apiResource.getPath());

            for (MethodDefinition method : methodDefinitions) {
                String response = method.getResponse();

                subResource.addMethod(method.getMethod().toString()).handledBy(new RestInflector(response));

            }


            for(ApiResource resource : apiResource.getResources()) {
                buildResourceTree(subResource, resource);
            }

        }

        return resourceBuilder.build();
    }

    private void buildResourceTree(Resource.Builder resourceBuilder, ApiResource apiResource) {
        Set<MethodDefinition> methodDefinitions = apiResource.getMethodDefinitions();
        Resource.Builder subResource = Resource.builder(apiResource.getPath());

        for (MethodDefinition method : methodDefinitions) {
            String response = method.getResponse();

            subResource.addMethod(method.getMethod().toString()).handledBy(new RestInflector(response));
        }


        for(ApiResource resource : apiResource.getResources()) {
            buildResourceTree(subResource, resource);
        }

        resourceBuilder.addChildResource(subResource.build());
    }
}
