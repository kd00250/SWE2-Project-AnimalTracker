package edu.westga.cs3211.animaltracker.model.ScientistModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestConstructor {

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Scientist(null, "123456789"));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Scientist("", "123456789"));
    }

    @Test
    void testBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Scientist(" ", "123456789"));
    }

    @Test
    void testNullID() {
        assertThrows(IllegalArgumentException.class, () -> new Scientist("John Doe", null));
    }

    @Test
    void testEmptyID() {
        assertThrows(IllegalArgumentException.class, () -> new Scientist("John Doe", ""));
    }

    @Test
    void testBlankID() {
        assertThrows(IllegalArgumentException.class, () -> new Scientist("John Doe", " "));
    }

    @Test
    void testValidScientist() {
        var scientist = new Scientist("John Doe", "123456789");

        assertAll(
                () -> {
                    assertEquals("John Doe", scientist.getName());
                    assertEquals("123456789", scientist.getId());
                }
        );
    }
}