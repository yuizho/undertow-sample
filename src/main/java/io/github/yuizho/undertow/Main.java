/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import java.io.IOException;
import javax.servlet.ServletException;

public class Main {

    public static void main(String... args) throws IOException, ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(Main.class.getClassLoader())
                .setContextPath("/undertow")
            .setResourceManager(new ClassPathResourceManager(Main.class.getClassLoader(), "static"))
                .setDeploymentName("test")
                .addServlets(
                        Servlets.servlet("JserseyContainer", JerseyContainer.class)
                                .addMapping("/webresources/*"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        PathHandler path = Handlers.path(Handlers.redirect("/undertow"))
                .addPrefixPath("/undertow", manager.start());

        Undertow server = Undertow.builder()
                .addHttpListener(8888, "0.0.0.0")
                .setHandler(path)
                .build();
        server.start();
        System.out.println("> push Enter key to stop this server");
        System.in.read();
        server.stop();
    }
}
