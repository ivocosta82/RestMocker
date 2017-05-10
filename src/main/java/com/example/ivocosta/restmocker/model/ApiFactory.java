package com.example.ivocosta.restmocker.model;

import com.example.ivocosta.restmocker.model.elements.ApiResource;
import com.example.ivocosta.restmocker.model.elements.MethodDefinition;

/**
 * Created by Ivo on 10/02/2017.
 */
public class ApiFactory {

    //TODO: Parse RAML into API structure

    public static Api getApi() {
        Api api = new Api();

        ApiResource resource = new ApiResource();
        resource.setPath("test");

        MethodDefinition methodDef = new MethodDefinition();
        methodDef.setMethod(Method.GET);
        methodDef.addResponses(StatusCode.OK, "\"Hello from the API\"");
        resource.addMethodDefinition(methodDef);

        ApiResource resource2 = new ApiResource();
        resource2.setPath("again");

        MethodDefinition methodDef2 = new MethodDefinition();
        methodDef2.setMethod(Method.GET);
        methodDef2.addResponses(StatusCode.OK, "\"Hello from the API Again\"");

        resource2.addMethodDefinition(methodDef2);
        resource.addResource(resource2);


        api.addResource(resource);
        return api;
    }
}
