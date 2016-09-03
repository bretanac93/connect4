/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class Utils {
    public static HashMap<String, Object> buildMessage(String title, Object value) {
        HashMap<String, Object> msg = new HashMap<>();
        msg.put(title, value);
        return msg;
    }
}
