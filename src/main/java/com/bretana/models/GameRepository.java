/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author user
 */
public interface GameRepository extends CrudRepository<Game, Long> {
    @Query("select g from Game g where g.player1.name = :player1 and g.player2.name = :player2 or g.player2.name = :player1 and g.player1.name = :player2")
    Optional<Game> findGameByVs(@Param("player1") String player1, @Param("player2") String player2);
}
