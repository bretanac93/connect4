/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.controllers;

import com.bretana.lib.Board;
import com.bretana.lib.StatusMessage;
import com.bretana.models.Game;
import com.bretana.models.GameRepository;
import com.bretana.models.Player;
import com.bretana.models.PlayerRepository;
import com.bretana.models.Turn;
import com.bretana.models.TurnRepository;
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
    
    @Autowired
    private TurnRepository turn_repository;
     
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
    
    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public ResponseEntity<?> makeTurn(@RequestBody Map<String, Integer> payload) {
        // Player 1, Player 2, token_position
        Player who_play = player_repository.findOne((long) payload.get("who_play"));
        String other_player = player_repository.findOne((long) payload.get("player1")).getName();
        Turn t = new Turn();
        
        Game g = repository.findGameByVs(who_play.getName(), other_player).get();
        int token_position = payload.get("token_position");
        long[][] board_matrix = this.decodeMatrix(g.getBoard());
        
        this.board.fillBoard(board_matrix);
        
        try {
            StatusMessage result = this.board.insertPiece(who_play.getId(), token_position);
            
            if (result != null) {
                result.setWinner(who_play.getName());
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            else {
                g.setBoard(this.encodeMatrix(this.board.getBoard()));
            
                t.setGame(g)
                 .setPlayer(who_play)
                 .setPos_x(token_position)
                 .setPos_y(this.board.getTokenCountByPos(token_position));
                
                repository.save(g);
                turn_repository.save(t);
                
                return new ResponseEntity<>("Nice", HttpStatus.CREATED);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
