package com.adamovichdev.movieawards.controler;

import com.adamovichdev.movieawards.api.authentication.AuthenticationRequest;
import com.adamovichdev.movieawards.security.AuthenticationService;
import com.adamovichdev.movieawards.security.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public String welcome() {
        return "You are authenticated!!";
    }

    @PostMapping("/authenticate")
    public JwtToken authenticate(@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {
        return authenticationService.authenticate(authRequest);
    }
}
