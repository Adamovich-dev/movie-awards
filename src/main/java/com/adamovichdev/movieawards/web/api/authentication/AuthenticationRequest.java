package com.adamovichdev.movieawards.web.api.authentication;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationRequest {

    //todo validation message in error
    @NotNull(message = "userName must to be present")
    @Size(max = 32, message = "userName must be less than 32 symbols")
    private String userName;

    @NotNull(message = "userName must to be present")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
