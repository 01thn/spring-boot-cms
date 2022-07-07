package com.thn.springbootcms.service;

import com.thn.springbootcms.security.AuthUserFactory;
import com.thn.springbootcms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with such username doesn't exist");
        }
        return AuthUserFactory.create(userOptional.get());
    }
}
