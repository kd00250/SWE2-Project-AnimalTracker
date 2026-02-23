package edu.westga.cs3211.animaltracker.model.login.service.localloginauth;

import edu.westga.cs3211.animaltracker.model.login.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.service.LocalLoginAuth;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    void testValidLoginAttempt() {
        var username = "tim";
        var password = "123";
        var request = new LoginRequest(username, password);
        var auth = new LocalLoginAuth();
        var response = auth.login(request);
        assertNotNull(response.getToken());
        assertEquals(LocalLoginAuth.DEFAULT_TIMEOUT, response.getTimeout());
    }

    @Test
    void testNullRequestOnLogin() {
        var auth = new LocalLoginAuth();
        assertThrows(IllegalArgumentException.class, () -> auth.login(null));
    }
}
