package com.example.ivocosta.restmocker.model.elements;

import com.example.ivocosta.restmocker.model.Method;

import java.util.List;
import java.util.Map;

/**
 * Created by ivo.costa@estafet.com on 09/02/2017.
 */
public class ApiResource {
    private List<ApiResource> resources;
    private Map<Method, MethodDefinition> methods;
}
