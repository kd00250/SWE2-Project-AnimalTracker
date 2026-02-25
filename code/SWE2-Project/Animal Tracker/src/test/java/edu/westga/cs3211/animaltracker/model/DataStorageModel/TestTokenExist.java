package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTokenExist {
    @AfterAll
    static void tearDown() {
        DataStorage.reset();
    }
    @Test
    void testGetUserNullToken() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataStorage.tokenExist(null);
        });
    }

    @Test
    void testStorageContainsUserKey() {
        var token =  DataStorage.generateTokenForUser(new User("Tim", "1515", Role.ADMIN));
        var actual = DataStorage.tokenExist(token);
        assertTrue(actual);
    }

    @Test
    void testStorageDoesNotContainUserKey() {
        DataStorage.reset();
        var actual = DataStorage.tokenExist("-1");
        assertFalse(actual);
    }
}
