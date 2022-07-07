package com.thn.springbootcms.security;

import com.thn.springbootcms.service.AuthUserService;
import com.thn.springbootcms.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    private String[] PUBLIC_ENDPOINTS = {
            "/user/sign-up",
            "/**/*.css",
            "/**/*.js",
            "/h2-console/**",
            "api/**"
    };


    private String LOGIN_ENDPOINT = "/user/sign-in";

    private String BOARD_ENDPOINT = "/user/board";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(LOGIN_ENDPOINT)
                .permitAll()
                .successHandler(authSuccessHandler)
                .and()
                .logout().permitAll()
                .and()
                .apply(new JWTConfig(jwtTokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder);
    }
}
