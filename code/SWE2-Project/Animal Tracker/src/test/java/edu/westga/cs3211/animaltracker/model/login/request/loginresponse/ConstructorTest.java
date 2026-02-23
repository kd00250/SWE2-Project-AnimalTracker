package edu.westga.cs3211.animaltracker.model.login.request.loginresponse;

import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {

    @Test
    void testValidConstructor() {
        var token = "1234";
        var timeout = 12;

        var response = new LoginResponse(token, timeout);
        assertAll(() -> {
            assertEquals("1234", response.getToken());
            assertEquals(timeout, response.getTimeout());
        });
    }

    @Test
    void testNullTokenDoesNotThrow() {
        var timeout = 12;
        assertDoesNotThrow(() -> {
            new LoginResponse(null, timeout);
        });
    }

    @Test
    void testEmptyTokenThrows() {
        var token = "";
        var timeout = 12;

        assertThrows(IllegalArgumentException.class, () -> {
            new LoginResponse(token, timeout);
        });
    }

    @Test
    void testNegativeTimeoutThrows() {
        var token = "1234";
        var timeout = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            new LoginResponse(token, timeout);
        });
    }
}
