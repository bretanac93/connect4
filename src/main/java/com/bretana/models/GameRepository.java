/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Actions for the Game entity
 * @author Cesar Bretana Gonzalez
 */
public interface GameRepository extends CrudRepository<Game, Long> {
    @Query("select g from Game g where g.player1.username = :player or g.player2.username = :player")
    Collection<Game> findGameByPlayer(@Param("player") String player);
}
