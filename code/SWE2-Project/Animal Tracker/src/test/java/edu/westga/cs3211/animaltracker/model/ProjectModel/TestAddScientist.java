package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Scientist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAddScientist {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testNullScientist() {
        var project = new Project();
        assertThrows(IllegalArgumentException.class, () -> project.addScientist(null));
    }

    @Test
    void testValidNewScientist() {
        var project = new Project();
        var scientist = new Scientist("John Doe", "123456789");

        project.addScientist(scientist);

        assertAll(() -> {
                    assertTrue(project.getScientists().contains(scientist));
                    assertEquals(scientist, project.getScientists().getFirst());
                }
        );
    }

    @Test
    void testAddExistingScientist() {
        var project = new Project();
        var scientist = new Scientist("John Doe", "123456789");
        project.addScientist(scientist);
        assertThrows(IllegalArgumentException.class, () -> project.addScientist(scientist));
    }
}