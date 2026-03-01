package edu.westga.cs3211.animaltracker.model.login.service.localserver;

import edu.westga.cs3211.animaltracker.model.login.service.LocalServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsValidTokenTest {
    @Test
    void testIsValidToken() {
        var auth = new LocalServer();
        assertTrue(auth.isValidToken("1234"));
    }

    @Test
    void testIsNullToken() {
        var auth = new LocalServer();
        assertFalse(auth.isValidToken(null));
    }

    @Test
    void testIsEmptyToken() {
        var auth = new LocalServer();
        assertFalse(auth.isValidToken(""));
    }
}
