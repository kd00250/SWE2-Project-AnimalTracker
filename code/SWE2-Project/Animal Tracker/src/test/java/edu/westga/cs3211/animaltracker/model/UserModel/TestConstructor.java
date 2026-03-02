package edu.westga.cs3211.animaltracker.model.UserModel;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestConstructor {

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "bob", Role.ADMIN));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "123456789", Role.SCIENTIST));
    }

    @Test
    void testBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new User(" ", "123456789", Role.CONTRIBUTOR));
    }

    @Test
    void testNullID() {
        assertThrows(IllegalArgumentException.class, () -> new User("John Doe", null, Role.GUEST));
    }

    @Test
    void testEmptyID() {
        assertThrows(IllegalArgumentException.class, () -> new User("John Doe", "", Role.ADMIN));
    }

    @Test
    void testBlankID() {
        assertThrows(IllegalArgumentException.class, () -> new User("John Doe", " ", Role.CONTRIBUTOR));
    }

    @Test
    void testValidUser() {
        var user = new User("John Doe", "123456789", Role.ADMIN);

        assertAll(
                () -> {
                    assertEquals("John Doe", user.getUsername());
                    assertEquals("123456789", user.getPassword());
                    assertEquals(Role.ADMIN, user.getRole());
                }
        );
    }
}
