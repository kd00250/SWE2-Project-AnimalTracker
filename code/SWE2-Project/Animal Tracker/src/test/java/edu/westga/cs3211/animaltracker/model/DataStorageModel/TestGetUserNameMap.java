package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGetUserNameMap {

    @Test
    void testGetGetUsernameMap() {
        DataStorage.getUsers().clear();
        DataStorage.getTokenMap().clear();
        DataStorage.getUsernameMap().clear();
        User user = new User("Tim", "1234", Role.SCIENTIST);
        DataStorage.generateTokenForUser(user);
        var users = DataStorage.getUsernameMap();
        assertNotNull(users);
        assertEquals(1, DataStorage.getUsernameMap().size());
    }
}
