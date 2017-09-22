/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author yuizho
 */
@Path("/hello")
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        System.out.println("ThreadId: " + Thread.currentThread().getId());
        System.out.println("HashCode: " + System.identityHashCode(this));
        return "hello resource !!";
    }
}
