package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TestGetID {

    @Test
    void getProjectID() {
        Project project = new Project(new ArrayList<>(), "test", new ArrayList<>(), 2);

        assertEquals(2, project.getId());
    }
}
