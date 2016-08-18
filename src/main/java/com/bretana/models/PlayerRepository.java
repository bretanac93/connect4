/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.models;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author user
 */

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findByName(String name);
}
