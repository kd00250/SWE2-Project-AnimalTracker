package edu.westga.cs3211.animaltracker.model.login.service.localserver;

import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.service.LocalServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    void testValidLoginAttempt() {
        var username = "Bob";
        var password = "1234";
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNotNull(response.getToken());
        assertEquals(LocalServer.DEFAULT_TIMEOUT, response.getTimeout());
    }

    @Test
    void testNullRequestOnLogin() {
        var auth = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> auth.login(null));
    }

    @Test
    void testUserFoundWrongPassword() {
        var username = "Bob";
        var password = "12345";
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNull(response);
    }

    @Test
    void testUserNotFound() {
        var username = "1616161616";
        var password = "12345";
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNull(response);
    }
}
