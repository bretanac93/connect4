/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.controllers;

import com.bretana.lib.Board;
import com.bretana.models.Game;
import com.bretana.models.GameRepository;
import com.bretana.models.Player;
import com.bretana.models.PlayerRepository;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/games")
public class GameController {
    
    @Autowired
    private GameRepository repository;
    
    @Autowired
    private PlayerRepository player_repository;
     
    private Board board;
    private final Gson gson = new Gson();
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Game>> getAllGames() {
        return new ResponseEntity<>((Collection<Game>) repository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getPlayerById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }
    
    private String encodeMatrix(long[][] m) {
        String data = gson.toJson(m);
        return data;
    }
    
    private long[][] decodeMatrix(String data) {
        long[][] m = gson.fromJson(data, long[][].class);
        return m;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createGame(@RequestBody Map<String, Integer> payload) throws Exception {
        Long id_p1 = (long) payload.get("id_p1");
        Long id_p2 = (long) payload.get("id_p2");
        int width = payload.get("width");
        int height = payload.get("height");
        
        Player p1 = player_repository.findOne(id_p1);
        Player p2 = player_repository.findOne(id_p2);
        
        Game g = new Game();
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        g.setBoard_width(width);
        g.setBoard_height(height);
        board = new Board(width, height);
        
        g.setBoard(this.encodeMatrix(board.getBoard()));
        
        return new ResponseEntity<>(repository.save(g), HttpStatus.CREATED);
    }
    
    @RequestMapping(params = {"player1", "player2"}, method = RequestMethod.GET)
    public ResponseEntity<Game> getGameByVs(@RequestParam("player1") String player1, @RequestParam("player2") String player2) {
        return new ResponseEntity<>(repository.findGameByVs(player1, player2).get(), HttpStatus.OK);
    }
}
