package com.example.ivocosta.restmocker.model;

/**
 * Created by ivo.costa@estafet.com on 09/02/2017.
 */
public enum Method {
    GET, PUT, POST, DELETE;

    public Method fromString(String method) {
        for (Method item : Method.values()) {
            if (item.toString().equalsIgnoreCase(method)) {
                return item;
            }
        }

        return GET;
    }
}
