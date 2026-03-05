package edu.westga.cs3211.animaltracker.model.server.request.auth.loginrequest.loginresponse;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {

    @Test
    void testValidConstructor() {
        var token = "1234";
        var timeout = 12;
        var creationTime = ZonedDateTime.now();
        var response = new LoginResponse(token, timeout, creationTime);
        assertAll(() -> {
            assertEquals("1234", response.getToken());
            assertEquals(timeout, response.getTimeout());
            assertEquals(creationTime, response.getCreationTime());
        });
    }

    @Test
    void testNullCreationTime() {
        var token = "1234";
        var timeout = 12;
        assertThrows(IllegalArgumentException.class, () -> {
            var response = new LoginResponse(token, timeout, null);
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
