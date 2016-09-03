/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.model.json.response;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -6624726180748515507L;
    private String token;

    public AuthenticationResponse() {
        super();
    }

    public AuthenticationResponse(String token) {
        this.setToken(token);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
