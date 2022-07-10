package com.thn.springbootcms;

import com.thn.springbootcms.entity.Role;
import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.repository.UserRepository;
import com.thn.springbootcms.service.UserService;
import com.thn.springbootcms.util.PasswordEncoderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoderUtil passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User(1L,
                "Testtest",
                "Test",
                "test@test.com",
                "Testtest",
                new HashSet<>() {{
                    add(Role.USER);
                }});
    }

    @Test
    public void saveNewUserAndGetItsObject() {
        when(userService.findUserByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userRepository.save(user)).thenReturn(user);
        var savedUser = userService.save(user);
        assertThat(savedUser).isPresent();
    }

}
