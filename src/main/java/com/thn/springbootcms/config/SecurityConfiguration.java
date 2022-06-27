package com.thn.springbootcms.config;

import com.thn.springbootcms.service.AuthUserService;
import com.thn.springbootcms.service.UserService;
import com.thn.springbootcms.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    private String[] PUBLIC_ENDPOINTS = {
            "/user/sign-up",
            "/**/*.css",
            "/**/*.js"
    };

    private String LOGIN_ENDPOINT = "/user/sign-in";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(LOGIN_ENDPOINT).permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder);
    }
}
