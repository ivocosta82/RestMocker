package com.example.ivocosta.restmocker.model.elements;

import com.example.ivocosta.restmocker.model.Method;

import java.util.*;
import java.util.stream.Collectors;

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

    public ApiResource(String path, Set<MethodDefinition> methods) {
        this.path = path;
        this.methods = methods;
        this.resources = new ArrayList<ApiResource>();
    }

    public List<ApiResource> getFlatView(String basePath) {
        ArrayList<ApiResource> flatView = new ArrayList<>();
        String absPath = basePath + "/" + this.path;
        flatView.add(new ApiResource(absPath, methods));

        List<ApiResource> childResources = resources.parallelStream()
                .map(res -> res.getFlatView(absPath))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        flatView.addAll(childResources);
        return flatView;
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
