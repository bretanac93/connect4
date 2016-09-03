/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bretana.auth.model.factory;

import com.bretana.auth.domain.entity.User;
import com.bretana.auth.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author user
 */
public class UserFactory {

    public static SecurityUser create(User user) {
        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastPasswordReset(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
        );
    }

}
