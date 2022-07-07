package com.thn.springbootcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthUser authUser = (AuthUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        request.getSession().setAttribute("username", authUser.getUsername());
        String token = jwtTokenProvider.generateToken(authUser.getUsername(), authUser.getRoleSet());
        String[] isSplitToken = token.split("\\.");
        request.getSession().setAttribute("token0", isSplitToken[0]);
        request.getSession().setAttribute("token1", isSplitToken[1]);
        request.getSession().setAttribute("token2", isSplitToken[2]);
        response.sendRedirect("/user/board");
    }
}
