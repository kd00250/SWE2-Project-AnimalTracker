package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGetUserNameMap {

    @Test
    void testGetUsernameMap() {
        assertEquals(2, DataStorage.getUsernameMap().size());
        assertEquals(DataStorage.getUsers().getFirst() ,DataStorage.getUsernameMap().get("Bob"));
        assertEquals(DataStorage.getUsers().get(1) ,DataStorage.getUsernameMap().get("Billy"));
    }
}
