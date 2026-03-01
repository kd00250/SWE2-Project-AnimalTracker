package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetScientists {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void getScientists() {
        assertEquals(1, DataStorage.getUsers().size());
    }
}
