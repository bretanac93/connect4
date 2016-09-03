/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.model.json.response;

import java.io.Serializable;

/**
 * Contains the information of the authentication Response
 * @author Cesar Bretana Gonzalez
 */
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -6624726180243515507L;
    private String token;

    /**
     * Create a new empty Authentication Response.
     */
    public AuthenticationResponse() {
        super();
    }

    /**
     * Create a new response with the token information already initialized
     * @param token
     */
    public AuthenticationResponse(String token) {
        this.setToken(token);
    }

    /**
     * Get the token
     * @return
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Modifies the token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
