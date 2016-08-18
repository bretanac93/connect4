/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.controllers;

import com.bretana.models.Player;
import com.bretana.models.PlayerRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/players")
public class PlayerController {
    
    @Autowired
    private PlayerRepository repository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Player>> getAllPlayers() {
        return new ResponseEntity<>((Collection<Player>) repository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }
    
    @RequestMapping(params = {"name"}, method = RequestMethod.GET)
    public ResponseEntity<Collection<Player>> getPlayerByName(@RequestParam(value="name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPlayer(@RequestBody Player input) {
        return new ResponseEntity<>(repository.save(input), HttpStatus.CREATED);
    }
}
