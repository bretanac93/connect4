/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.domain.entity;

import com.bretana.models.Turn;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Holds all the information regarding to the users.
 * @author Cesar Bretana Gonzalez
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date lastPasswordReset;
    private String authorities;
    private String country;
    private List<Turn> moves;
    
    /**
     * Constructs a null User.
     */
    public User() {
        super();
    }

    /**
     * Constructs a new User by passing all the information
     * @param username User Name
     * @param password Password
     * @param email Email
     * @param country Country
     * @param lastPasswordReset Date indicating last time password was reset.
     * @param authorities Comma separated values. Contains the roles.
     */
    public User(String username, String password, String email, String country, Date lastPasswordReset, String authorities) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }

    /**
     * Get id of the User
     * @return id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    /**
     * Set id of the user
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Username of the user.
     * @return username
     */
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    /**
     * Set the user name
     * @param username New username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Modify the password of the user.
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get email of the user.
     * @return email
     */
    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    /**
     * Modify email of the user
     * @param email New email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get last date that password was reset.
     * @return date
     */
    @Column(name = "last_password_reset")
    public Date getLastPasswordReset() {
        return this.lastPasswordReset;
    }
    
    /**
     * Modify last date password reset.
     * @param lastPasswordReset
     */
    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    /**
     * Get CSV containing all the roles assigned to the user
     * @return String
     */
    @Column(name = "authorities")
    public String getAuthorities() {
        return this.authorities;
    }

    /**
     * Modify authorities
     * @param authorities the new CSV of authorities
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
    
    /**
     * Get the user country
     * @return
     */
    public String getCountry() {
        return this.country;
    }
    
    /**
     * Modify the user country
     * @param country The new country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Get a List of moves made by the user.
     * @return
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public List<Turn> getMoves() {
        return moves;
    }

    /**
     * Modify the list of moves.
     * @param moves the new list
     */
    public void setMoves(List<Turn> moves) {
        this.moves = moves;
    }
}
