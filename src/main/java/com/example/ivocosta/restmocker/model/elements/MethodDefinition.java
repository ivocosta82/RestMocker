package com.example.ivocosta.restmocker.model.elements;

import com.example.ivocosta.restmocker.model.StatusCode;

import java.util.Map;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public class MethodDefinition {
    private String description;
    private BodyDefinition requestBody;
    private Map<StatusCode, BodyDefinition> responses;
}
