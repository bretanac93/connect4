/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.lib;

/**
 * Defines a struct for indicate status of the game
 * @author Cesar Bretana Gonzalez
 */
public class StatusMessage {
    private String direction;
    private String winner;
    
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
    
    public StatusMessage(String direction, String winner) {
        this.direction = direction;
        this.winner = winner;
    }
    
}
