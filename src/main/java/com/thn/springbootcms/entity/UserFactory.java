package com.thn.springbootcms.entity;

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
