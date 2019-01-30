package com.example.ivocosta.restmocker;

import com.example.ivocosta.restmocker.model.ApiFactory;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.InstanceFactory;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;

/**
 * Created by Ivo Costa on 09/02/2017.
 */
public class RestMocker {
    public static void main(String args[]) throws Exception {

        DeploymentInfo servletBuilder = Servlets.deployment();

        InstanceFactory<ServletContainer> factory = new RestServletFactory(ApiFactory.getApi());

        servletBuilder
                .setClassLoader(Application.class.getClassLoader())
                .setContextPath("/mss")
                .setDeploymentName("mss.war")
                .addServlets(Servlets.servlet("jerseyServlet", ServletContainer.class, factory)
                        .setLoadOnStartup(1)
                        .addMapping("/api/*"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        PathHandler path = Handlers.path(Handlers.redirect("/mss"))
                .addPrefixPath("/mss", manager.start());

        Undertow server = Undertow.builder().addHttpListener(9999, "localhost")
                .setHandler(path).build();


        server.start();
    }
}
