package com.thn.springbootcms.config;

import com.thn.springbootcms.service.AuthUserService;
import com.thn.springbootcms.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    private String[] PUBLIC_ENDPOINTS = {
            "/user/sign-up",
            "/**/*.css",
            "/**/*.js",
            "/h2-console/**",
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
                .defaultSuccessUrl(BOARD_ENDPOINT, true)
                .permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder);
    }
}
