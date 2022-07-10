package com.thn.springbootcms;

import com.thn.springbootcms.entity.User;
import com.thn.springbootcms.service.AuthUserService;
import com.thn.springbootcms.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthUserServiceTests {
    @InjectMocks
    private AuthUserService authUserService;

    @Mock
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setId(1L);
        user.setUsername("Testtest");
        user.setEmail("test@test.com");
        user.setPassword("testtest");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mockUserAndGetUserDetails() {
        when(userService.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));
        var userDetails = authUserService.loadUserByUsername(user.getUsername());
        assertThat(userDetails).isNotNull();
    }
}
