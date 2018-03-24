/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yuizho.undertow;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author yuizho
 */
public class MyException extends WebApplicationException{
    Response response;
    public MyException(Response response) {
        super();
        this.response = response;
    }
}
