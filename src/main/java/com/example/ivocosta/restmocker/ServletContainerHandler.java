package com.example.ivocosta.restmocker;

import io.undertow.servlet.api.InstanceHandle;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by Ivo Costa on 10/02/2017.
 */
public class ServletContainerHandler implements InstanceHandle<ServletContainer> {
    private ServletContainer container;

    public ServletContainerHandler(ServletContainer container) {
        this.container = container;
    }

    public ServletContainer getInstance() {
        return container;
    }

    public void release() {
        container.destroy();
    }
}
