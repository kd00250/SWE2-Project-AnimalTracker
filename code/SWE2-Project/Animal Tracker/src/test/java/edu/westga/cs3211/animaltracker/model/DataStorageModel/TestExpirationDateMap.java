package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestExpirationDateMap {

    @Test
    void testGetExpirationDateMap() {
        assertEquals(0, DataStorage.getExpirationDateMap().size());
    }
}
