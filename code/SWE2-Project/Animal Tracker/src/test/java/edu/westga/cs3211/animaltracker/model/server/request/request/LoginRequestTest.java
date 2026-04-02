package edu.westga.cs3211.animaltracker.model.server.request.request;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.LOGIN_REQUEST;

class LoginRequestTest {

    @Test
    void testToJsonValidRequest() {
        String username = "kenny";
        String password = "1234";
        LoginRequest request = new LoginRequest(username, password);

        // Act
        JSONObject json = request.toJson();

        // Assert
        assertNotNull(json);
        assertEquals(LOGIN_REQUEST, json.getString("action"));
        assertEquals(username, json.getString("username"));
        assertEquals(password, json.getString("password"));
    }
}
