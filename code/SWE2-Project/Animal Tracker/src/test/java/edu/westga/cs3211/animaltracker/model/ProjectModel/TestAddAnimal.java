package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Scientist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TestAddAnimal {

    @Test
    void testAddNullAnimal() {
        var project = new Project();
        assertThrows(IllegalArgumentException.class, () -> {
            project.addAnimal(null);
        });
    }

    @Test
    void testAddValidAnimal() {
        var project = new Project();
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
    void testAddExisingAnimal() {
        var animals = new LinkedList<Animal>();
        var animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        animals.add(animal);
        var project = new Project("Project", new ArrayList<Scientist>(), animals);

        assertThrows(IllegalArgumentException.class, () -> {
            project.addAnimal(animal);
        });
    }
}