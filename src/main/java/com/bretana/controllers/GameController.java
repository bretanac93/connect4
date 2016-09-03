/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.controllers;

import com.bretana.Constants;
import com.bretana.auth.domain.entity.User;
import com.bretana.auth.repository.UserRepository;
import com.bretana.auth.security.TokenUtils;
import com.bretana.lib.Board;
import com.bretana.lib.StatusMessage;
import com.bretana.models.Game;
import com.bretana.models.GameRepository;

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
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/games")
public class GameController {

    private final String tokenHeader = Constants.AUTH_HEADER;

    @Autowired
    private GameRepository repository;


    @Autowired
    private TurnRepository turn_repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;

    private Board board;
    private final Gson gson = new Gson();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Game>> getAllGames() {
        return new ResponseEntity<>((Collection<Game>) repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    public ResponseEntity<?> createGame(HttpServletRequest request, @RequestBody Map<String, Integer> payload) throws Exception {
        String token = request.getHeader(this.tokenHeader).split(" ")[1];
        String username = tokenUtils.getUsernameFromToken(token);

        int width = payload.get("width");
        int height = payload.get("height");

        User player1 = userRepository.findByUsername(username);

        Game g = new Game();

        g.setPlayer1(player1);

        g.setBoard_width(width);
        g.setBoard_height(height);
        board = new Board(width, height);

        g.setBoard(this.encodeMatrix(board.getBoard()));

        return new ResponseEntity<>(repository.save(g), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public ResponseEntity<?> makeTurn(HttpServletRequest request, @RequestBody Map<String, Integer> payload) {

        String token = request.getHeader(this.tokenHeader).split(" ")[1];
        String username = tokenUtils.getUsernameFromToken(token);
        // Player 1, Player 2, token_position
        
        User who_play = userRepository.findByUsername(username);
        Game g = repository.findOne((long) payload.get("game_id"));
        
        if (g.getPlayer2() == null) {
            return new ResponseEntity<>(buildMessage("message", "You cannot begin this game until another player joins."), HttpStatus.BAD_REQUEST);
        }

        if (g.getBoard_height() * g.getBoard_width() == g.getTurns().size()) {
            g.hasFinished();
            return new ResponseEntity<>(new StatusMessage("DRAW", ""), HttpStatus.NOT_MODIFIED);
        }

        if (g.isFinished()) {
            return new ResponseEntity<>(buildMessage("message", "This game is already finished, you cannot keep playing on."), HttpStatus.NOT_MODIFIED);
        }

        Turn t = new Turn();

        int token_position = payload.get("token_position");
        long[][] board_matrix = this.decodeMatrix(g.getBoard());

        this.board.fillBoard(board_matrix);

        try {
            StatusMessage result = this.board.insertPiece(who_play.getId(), token_position);

            if (result != null) {
                result.setWinner(who_play.getUsername());
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                g.setBoard(this.encodeMatrix(this.board.getBoard()));

                t.setGame(g)
                        .setUser(who_play)
                        .setPos_x(token_position)
                        .setPos_y(this.board.getTokenCountByPos(token_position));

                Game g_saved = repository.save(g);
                turn_repository.save(t);

                return new ResponseEntity<>(g_saved, HttpStatus.CREATED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private HashMap<String, Object> buildMessage(String title, Object value) {
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(title, value);
        return msg;
    } 
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> joinGame(HttpServletRequest request, @PathVariable long id) {
        Game game = repository.findOne(id);
        String token = request.getHeader(this.tokenHeader).split(" ")[1];
        String username = tokenUtils.getUsernameFromToken(token);
        User player = userRepository.findByUsername(username);
        
        if (player == game.getPlayer1()) {
            return new ResponseEntity<>(buildMessage("message", "You cannot play against yourself"), HttpStatus.BAD_REQUEST);
        }
        
        if (game.getPlayer2() != null) {
            return new ResponseEntity<>(buildMessage("message", "This game is full, find yourself another one"), HttpStatus.BAD_REQUEST);
        }
        
        game.setPlayer2(player);
        repository.save(game);
        
        return new ResponseEntity<>(buildMessage("message", "Joined to the game successfully, enjoy it!"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/joined")
    public ResponseEntity<Collection<Game>> getGamesByPlayer(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader).split(" ")[1];
        String username = tokenUtils.getUsernameFromToken(token);
        
        Collection<Game> games = repository.findGameByPlayer(username);
        
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}
