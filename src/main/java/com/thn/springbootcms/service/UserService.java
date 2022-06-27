package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Role;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.UserRepository;
import com.thn.springbootcms.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    public User save(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoleSet(roles);
        return userRepository.save(newUser);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
