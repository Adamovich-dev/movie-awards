package com.adamovichdev.movieawards.security;

import com.adamovichdev.movieawards.security.jwt.JwtToken;
import com.adamovichdev.movieawards.security.jwt.JwtTokenProvider;
import com.adamovichdev.movieawards.web.api.authentication.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public JwtToken authenticate(AuthenticationRequest authRequest) {
        //        try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        } catch (Exception ex) {
//            throw new Exception("inavalid username or password");
//        }
        return new JwtToken(tokenProvider.createToken(authentication));
    }
}
