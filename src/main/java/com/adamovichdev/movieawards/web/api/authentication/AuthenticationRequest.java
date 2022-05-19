package com.adamovichdev.movieawards.web.api.authentication;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User's authentication request
 */
public class AuthenticationRequest {

    /**
     * User name (login)
     */
    @NotNull(message = "user name can not be empty")
    @Size(max = 32, message = "user name must be less than 32 symbols")
    private String userName;

    /**
     * Password
     */
    @NotNull(message = "password can not be empty")
    @Size(max = 32, message = "user name must be less than 32 symbols")
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
