package com.example.ivocosta.restmocker.model.elements;

import com.example.ivocosta.restmocker.model.Method;

import java.util.*;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public class ApiResource {
    private List<ApiResource> resources;

    private Set<MethodDefinition> methods;
    private String path;

    public ApiResource() {
        resources = new ArrayList<ApiResource>();
        methods = new HashSet<MethodDefinition>();
    }

    public void addResource(ApiResource resource) {
        resources.add(resource);
    }

    public void addMethodDefinition(MethodDefinition definition) {
        methods.add(definition);
    }

    public Set<MethodDefinition> getMethodDefinitions() {
        return methods;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ApiResource> getResources() {
        return resources;
    }

    public void setResources(List<ApiResource> resources) {
        this.resources = resources;
    }
}
