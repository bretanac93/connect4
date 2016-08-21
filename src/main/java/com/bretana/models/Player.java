/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author user
 */
@Entity
public class Player implements Serializable {
    
    private long id;
    
    private String name;
    private String country;
    private List<Turn> moves;
    
    public Player() {}
    public Player(String name) {
        this.name = name;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    } 
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    
    @Override
    public String toString() {
        return this.name;
    }

    @OneToMany(mappedBy = "player")
    public List<Turn> getMoves() {
        return moves;
    }

    public void setMoves(List<Turn> moves) {
        this.moves = moves;
    }
}
