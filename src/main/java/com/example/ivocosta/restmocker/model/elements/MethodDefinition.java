package com.example.ivocosta.restmocker.model.elements;

import com.example.ivocosta.restmocker.model.Method;
import com.example.ivocosta.restmocker.model.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public class MethodDefinition {
    private String description;

    private Method method;

    private BodyDefinition requestBody;

    private Map<StatusCode, String> responses;

    public MethodDefinition() {
        responses = new HashMap<StatusCode, String>();
    }

    public void addResponses(StatusCode code, String body) {
        responses.put(code, body);
    }

    public Method getMethod() {
        return method;
    }

    public String getResponse() {
        return responses.get(StatusCode.OK);
    }

    public void setMethod(Method method) {
        this.method = method;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodDefinition that = (MethodDefinition) o;

        return method == that.method;
    }

    @Override
    public int hashCode() {
        return method.hashCode();
    }
}
