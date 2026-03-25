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

        assertEquals(2, DataStorage.getUsers().size());
        assertEquals("Bob", DataStorage.getUsers().getFirst().username());
        assertEquals("1234", DataStorage.getUsers().getFirst().password());
        assertEquals(Role.SCIENTIST, DataStorage.getUsers().getFirst().role());
        assertEquals("Billy", DataStorage.getUsers().get(1).username());
        assertEquals("6767", DataStorage.getUsers().get(1).password());
        assertEquals(Role.ADMIN, DataStorage.getUsers().get(1).role());
    }
}
