package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGetUserNameMap {

    @Test
    void testGetUsernameMap() {
        assertEquals(0, DataStorage.getUsernameMap().size());
    }
}
