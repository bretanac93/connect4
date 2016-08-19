/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author user
 */
@Entity
public class Game implements Serializable {
    
    private Long id;
    private Player player1;
    private Player player2;
    
    public Game() {}
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
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
}
