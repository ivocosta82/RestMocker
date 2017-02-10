package com.example.ivocosta.restmocker.model;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public enum StatusCode {
    OK(200), NOT_FOUND(404);

    private int code;

    StatusCode(int i) {
        code = i;
    }

    public StatusCode fromCode(int i) {
        for(StatusCode status : StatusCode.values()){
            if(status.code == i) return status;
        }

        return NOT_FOUND;
    }
}
