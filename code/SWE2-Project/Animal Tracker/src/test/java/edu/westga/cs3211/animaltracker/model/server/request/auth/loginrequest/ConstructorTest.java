package edu.westga.cs3211.animaltracker.model.server.request.auth.loginrequest;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest {
    @Test
    void testValidConstructor() {
        String username = "Time";
        String password = "password";
        LoginRequest loginRequest = new LoginRequest(username, password);
        assertAll(() -> {
            assertEquals("Time", username);
        }, () -> {
            assertEquals("password", password);
        });
    }



}
