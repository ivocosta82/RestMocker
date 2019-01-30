package com.example.ivocosta.restmocker.model;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public enum Method {
    GET, PUT, POST, DELETE, OPTIONS;

    public Method fromString(String method) {
        for (Method item : Method.values()) {
            if (item.toString().equalsIgnoreCase(method)) {
                return item;
            }
        }

        return GET;
    }
}
