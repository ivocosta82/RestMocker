package com.example.ivocosta.restmocker.model;

import com.example.ivocosta.restmocker.model.elements.ApiResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public class Api {
    private List<ApiResource> resources;

    public Api() {
        resources = new ArrayList<ApiResource>();
    }

    public void addResource(ApiResource resource) {
        resources.add(resource);
    }

    public List<ApiResource> getResources() {
        return resources;
    }
}
