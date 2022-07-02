package com.thn.springbootcms.service;

import com.thn.springbootcms.entity.Role;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.UserRepository;
import com.thn.springbootcms.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    public Optional<User> save(User newUser) {
        if (findUserByUsername(newUser.getUsername()).isPresent()) {
            return Optional.empty();
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoleSet(roles);
        return Optional.of(userRepository.save(newUser));
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
