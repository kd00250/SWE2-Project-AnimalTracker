package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetNextProjectID {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testGetNextProjectIDEmpty() {
        assertEquals(1, DataStorage.getNextProjectId());
    }

    @Test
    void testGetNextAnimalIDNotEmpty() {
        Project project = new Project("Whales");
        assertEquals(2, DataStorage.getNextProjectId());
        assertEquals(DataStorage.getProjects().get(1), project);
    }
}
