/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.model.json.request;

import java.io.Serializable;

/**
 * Contains the information needed for the authentication.
 * @author Cesar Bretana Gonzalez
 */
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 6624726180758614907L;
    private String username;
    private String password;

    /**
     * Creates a new empty Authentication Request
     */
    public AuthenticationRequest() {
        super();
    }

    /**
     * Creates a new Authentication Request containing the login information
     * @param username
     * @param password
     */
    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     * Get the username
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Modify the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Modify the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
