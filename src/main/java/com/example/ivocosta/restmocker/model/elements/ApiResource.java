package com.example.ivocosta.restmocker.model.elements;

import com.example.ivocosta.restmocker.model.Method;

import java.util.List;
import java.util.Map;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public class ApiResource {
    private List<ApiResource> resources;
    private Map<Method, MethodDefinition> methods;
}
