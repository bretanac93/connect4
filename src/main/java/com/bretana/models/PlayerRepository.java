/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author Cesar Bretana
 */

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByName(String name);
    List<Player> findByCountry(String country);
}

