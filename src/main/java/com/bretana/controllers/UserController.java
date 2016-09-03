///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.bretana.controllers;

import com.bretana.auth.domain.entity.User;
import com.bretana.auth.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author user
// */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> userList = userService.getAllUsers();

        return ResponseEntity.ok(userList);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByName(@PathVariable("username") String username) {

        User aUser = userService.getUserByUsername(username);

        return ResponseEntity.ok(aUser);
    }
}
