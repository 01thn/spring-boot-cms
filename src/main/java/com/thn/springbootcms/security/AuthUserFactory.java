package com.thn.springbootcms.security;

import com.thn.springbootcms.entity.User;

public class AuthUserFactory {
    public static AuthUser create(User user) {
        return new AuthUser(user.getId(),
                user.getUsername(),
                user.getRealName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoleSet());
    }
}
