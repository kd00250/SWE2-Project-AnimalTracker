package edu.westga.cs3211.animaltracker.model.login.service.localserver;

import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.service.LocalServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    void testValidLoginAttempt() {
        var username = "tim";
        var password = "123";
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
}
