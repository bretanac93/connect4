/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.repository;

import com.bretana.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to perform actions with the User entities.
 * @author Cesar Bretana Gonzalez
 */
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
