package com.adamovichdev.movieawards.security.jwt;

import java.io.Serializable;

public class JwtToken implements Serializable {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
