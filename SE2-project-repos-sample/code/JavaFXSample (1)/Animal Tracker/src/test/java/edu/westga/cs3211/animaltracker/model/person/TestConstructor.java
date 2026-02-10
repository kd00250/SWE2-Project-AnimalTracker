package edu.westga.cs3211.animaltracker.model.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Person;

class TestConstructor {

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(null);
        });
    }

    @Test
    void testValidName() {
        Person result = new Person("Bob");

        assertEquals("Bob", result.getName(), "checking the name of the person");
    }

}
