/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.service;

import com.bretana.auth.domain.entity.User;
import java.util.List;

/**
 * Define the service to retrieve information from the users table.
 * @author Cesar Bretana Gonzalez
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
