package edu.westga.cs3211.animaltracker.model.login.service.localloginauth;

import edu.westga.cs3211.animaltracker.model.login.service.LocalLoginAuth;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsValidTokenTest {
    @Test
    void testIsValidToken() {
        var auth = new LocalLoginAuth();
        assertTrue(auth.isValidToken("1234"));
    }

    @Test
    void testIsNullToken() {
        var auth = new LocalLoginAuth();
        assertFalse(auth.isValidToken(null));
    }

    @Test
    void testIsEmptyToken() {
        var auth = new LocalLoginAuth();
        assertFalse(auth.isValidToken(""));
    }
}
