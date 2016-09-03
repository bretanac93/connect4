/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.service;

import com.bretana.auth.domain.entity.User;
import java.util.List;

/**
 *
 * @author user
 */
/**
 * Created by nasir on 6/2/16.
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
