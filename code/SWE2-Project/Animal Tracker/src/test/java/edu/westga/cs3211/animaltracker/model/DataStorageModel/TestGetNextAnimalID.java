package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import static org.junit.jupiter.api.Assertions.*;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGetNextAnimalID {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testGetNextAnimalIDEmpty() {
        DataStorage.getAnimals().clear();
        assertEquals(1, DataStorage.getNextAnimalId());
    }

    @Test
    void testGetNextAnimalIDNotEmpty() {
        assertEquals(2, DataStorage.getNextAnimalId());
    }
}
