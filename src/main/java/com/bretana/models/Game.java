/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import com.bretana.lib.Board;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author user
 */
@Entity
public class Game implements Serializable {
    
    private Long id;
    private Player player1;
    private Player player2;
    private Integer board_width;
    private Integer board_height;
    private List<Turn> turns;
    private Board board;
    
    
    public Game() {}
    public Game(Player player1, Player player2, Integer board_width, Integer board_height) {
        this.player1 = player1;
        this.player2 = player2;
        this.board_height = board_height;
        this.board_width = board_width;
        this.board = new Board(board_width, board_height);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne
    public Player getPlayer1() {
        return this.player1;
    }
    
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    @ManyToOne
    public Player getPlayer2() {
        return this.player2;
    }
    
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    public Integer getBoard_width() {
        return board_width;
    }

    public void setBoard_width(Integer board_width) {
        this.board_width = board_width;
    }

    public Integer getBoard_height() {
        return board_height;
    }

    public void setBoard_height(Integer board_height) {
        this.board_height = board_height;
    }

    @OneToMany(mappedBy = "game")
    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }
    
    public void addTurn(Turn turn) {
        long p_id = turn.getPlayer().getId();
        int pos_ins = turn.getPos_x();
        
        try {
            boolean was = this.board.insertPiece(p_id, pos_ins);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
