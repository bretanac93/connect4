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
public class Turn implements Serializable {
    private Long id;
    
    private Player player;
    
    private Integer pos_x;
    private Integer pos_y;
    
    private Game game;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Player getPlayer() {
        return player;
    }

    public Turn setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Integer getPos_x() {
        return pos_x;
    }

    public Turn setPos_x(Integer pos_x) {
        this.pos_x = pos_x;
        return this;
    }

    public Integer getPos_y() {
        return pos_y;
    }

    public Turn setPos_y(Integer pos_y) {
        this.pos_y = pos_y;
        return this;
    }
    
    @ManyToOne
    public Game getGame() {
        return game;
    }

    public Turn setGame(Game game) {
        this.game = game;
        return this;
    }
    
}
