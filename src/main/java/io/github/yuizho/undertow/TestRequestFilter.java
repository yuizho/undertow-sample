/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author yuizho
 */
@CookieAppender
@Provider
public class TestRequestFilter implements ContainerResponseFilter {
    @Context
    HttpServletResponse httpServletResponse;
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responesContext)
            throws IOException {
        System.out.println(System.identityHashCode(this));
        System.out.println(httpServletResponse instanceof ThreadLocal);
        System.out.println(System.identityHashCode(httpServletResponse));

        Cookie cookie = new Cookie("csrf-token", "aaaaaaaaaa");
        cookie.setMaxAge(30);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
    }
}
