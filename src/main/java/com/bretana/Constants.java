/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana;

/**
 * Contains all the defined constants variables of the project.
 * @author Cesar Bretana
 */
public class Constants {

    /**
     * Secret key to encode the token after conform it.
     */
    public static final String SECRET_KEY = "ThisTokenIsNoSecret";

    /**
     * Authorization header name, used the common one, but it can be configured by change it it's value here.
     */
    public static final String AUTH_HEADER = "Authorization";

    /**
     * Expiration time, 7 days by default.
     */
    public static final Long EXP_TIME = (long)604800;
}
