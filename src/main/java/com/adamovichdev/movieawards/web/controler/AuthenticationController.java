package com.adamovichdev.movieawards.web.controler;

import com.adamovichdev.movieawards.security.AuthenticationService;
import com.adamovichdev.movieawards.security.jwt.JwtToken;
import com.adamovichdev.movieawards.web.api.authentication.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public JwtToken authenticate(@Valid @RequestBody AuthenticationRequest authRequest) {
        logger.debug("Call method authenticate, login = " + authRequest.getUserName());
        return authenticationService.authenticate(authRequest);
    }
}
