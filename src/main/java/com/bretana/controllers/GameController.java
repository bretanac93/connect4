/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.controllers;

import com.bretana.models.Game;
import com.bretana.models.GameRepository;
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
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author user
 */
@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository repository;
    private PlayerRepository player_repository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Game>> getAllGames() {
        return new ResponseEntity<>((Collection<Game>) repository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getPlayerById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody String id_player1, @RequestBody String id_player2) {
        Player p1 = player_repository.findOne(Long.parseLong(id_player1));
        Player p2 = player_repository.findOne(Long.parseLong(id_player2));
        
        Game g = new Game();
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        
        return new ResponseEntity<>(repository.save(g), HttpStatus.CREATED);
    }
}
