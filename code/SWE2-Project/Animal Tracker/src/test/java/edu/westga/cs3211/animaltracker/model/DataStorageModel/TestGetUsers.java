package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetUsers {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void getUsers() {

        assertEquals(1, DataStorage.getUsers().size());
        assertEquals("Bob", DataStorage.getUsers().getFirst().getUsername());
        assertEquals("1234", DataStorage.getUsers().getFirst().getPassword());
        assertEquals(Role.SCIENTIST, DataStorage.getUsers().getFirst().getRole());
    }
}
