package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestContainsAnimalClass {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testContainsAnimalClass() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        var animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        project.addAnimal(animal);

        assertAll(() -> {
            assertTrue(project.containsAnimal(animal));
            assertEquals(1, project.getAnimals().size());
            assertEquals(animal, project.getAnimals().getFirst());
            assertTrue(project.containsAnimalType(AnimalClass.BIRD));
        });
    }

    @Test
    void testContainsAnimalClassNullClass() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> {
            project.containsAnimalType(null);
        });
    }
}