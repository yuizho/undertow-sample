/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author yuizho
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException> {

    @Override
    public Response toResponse(MyException e) {
        System.out.println(e.response);
        return e.response;
    } 
}
