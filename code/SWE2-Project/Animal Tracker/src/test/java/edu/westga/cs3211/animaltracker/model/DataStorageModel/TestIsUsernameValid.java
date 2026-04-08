package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestIsUsernameValid {
    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void validGetUsername() {
        assertTrue(DataStorage.isUsernameAvailable("Kaz"));
    }

    @Test
    void invalidGetUsername() {
        User user = new User("Kaz", "1234", Role.ADMIN);
        DataStorage.getUsers().add(user);
        assertFalse(DataStorage.isUsernameAvailable("Kaz"));
    }
}
