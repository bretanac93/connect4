/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.service;

/**
 * Service for handle all the security matters, check if an user have an specific role
 * @author Cesar Bretana Gonzalez
 */
public interface SecurityService {

    /**
     * Check if the user authenticated have access to certain action.
     * @return True if have access, False otherwise
     */
    public Boolean hasProtectedAccess();
}
