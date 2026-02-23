package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGetID {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void getProjectID() {
        Project project = new Project("Hello World");

        assertEquals(1, project.getId());
    }
}
