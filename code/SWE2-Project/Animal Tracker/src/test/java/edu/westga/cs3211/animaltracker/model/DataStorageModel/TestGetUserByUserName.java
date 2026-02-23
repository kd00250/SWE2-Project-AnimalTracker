package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGetUserByUserName {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testGetUserByUserName() {
        User user = new User("bob", "toon", Role.ADMIN);
        assertEquals(user, DataStorage.getUserByUsername(user.getUsername()));
    }
}
