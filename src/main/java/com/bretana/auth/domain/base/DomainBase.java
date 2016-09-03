/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.domain.base;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class DomainBase implements Serializable {

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
