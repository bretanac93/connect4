/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import com.bretana.auth.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private User player1;
    private User player2;
    private Integer board_width;
    private Integer board_height;
    private boolean finished;

    
    private List<Turn> turns;
    private String board;

    public Game() {}
    public Game(User player1, User player2, Integer board_width, Integer board_height) {
        this.player1 = player1;
        this.player2 = player2;
        this.board_height = board_height;
        this.board_width = board_width;
        this.board = "";
        this.finished = false;
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
    public User getPlayer1() {
        return this.player1;
    }
    
    public void setPlayer1(User player1) {
        this.player1 = player1;
    }
    
    @ManyToOne
    public User getPlayer2() {
        return this.player2;
    }
    
    public void setPlayer2(User player2) {
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
    @JsonIgnore
    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }
    
    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
    
    public boolean isFinished() {
        return finished;
    }

    public void hasFinished() {
        this.finished = true;
        this.turns.clear();
    }
    
    public void setFinished(boolean finished) {
        this.finished = finished;
        if (finished) this.turns.clear();
    }
}
