/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author yuizho
 */
public class JerseyContainer extends ServletContainer {

    public JerseyContainer() {
        super(new JerseyResourceConfig());
    }

    public static class JerseyResourceConfig extends ResourceConfig {

        public JerseyResourceConfig() {
            packages(this.getClass().getPackage().getName());
        }
    }
}
