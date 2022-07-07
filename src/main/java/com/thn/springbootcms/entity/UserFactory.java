package com.thn.springbootcms.entity;

import com.thn.springbootcms.security.AuthUser;

public class UserFactory {
    public static User create(AuthUser user) {
        return new User(user.getId(),
                user.getUsername(),
                user.getRealName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoleSet());
    }
}
