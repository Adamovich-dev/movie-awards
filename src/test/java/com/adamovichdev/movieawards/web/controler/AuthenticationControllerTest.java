package com.adamovichdev.movieawards.web.controler;

import com.adamovichdev.movieawards.security.AuthenticationService;
import com.adamovichdev.movieawards.security.jwt.JwtToken;
import com.adamovichdev.movieawards.web.api.authentication.AuthenticationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    private AuthenticationController controller;

    @Before
    public void init() {
        controller = new AuthenticationController(authenticationService);
    }

    @Test
    public void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest();
        when(authenticationService.authenticate(request)).thenReturn(new JwtToken(""));

        JwtToken token = controller.authenticate(request);

        verify(authenticationService).authenticate(request);
        assertNotNull(token);
    }
}
