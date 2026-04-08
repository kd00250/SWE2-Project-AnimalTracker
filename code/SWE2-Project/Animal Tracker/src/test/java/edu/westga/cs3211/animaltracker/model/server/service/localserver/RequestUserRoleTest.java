package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestUserRoleTest {
    private LoginResponse validResponse;

    @BeforeEach
    void setup() {
        var username = "Bob";
        var password = "1234";
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        this.validResponse = auth.login(request);
    }

    @Test
    void testNullRequest() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.requestUserRole(null);
        });
    }

    @Test
    void testInvalidTokenRequest() {
        var invalidToken = "-1";
        var auth = new LocalServer();
        var response = auth.requestUserRole(new UserDataRequest(invalidToken));
        assertNull(response);
    }

    @Test
    void validTokenReturnsGetRole() {
        var auth = new LocalServer();
        var request = new UserDataRequest(this.validResponse.getToken());
        var response = auth.requestUserRole(request);
        assertEquals(Role.SCIENTIST, response);
    }

}
