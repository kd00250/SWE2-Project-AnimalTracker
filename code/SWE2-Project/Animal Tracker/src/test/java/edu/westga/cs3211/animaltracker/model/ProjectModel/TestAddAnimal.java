package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TestAddAnimal {
    private Project project;
    @BeforeEach
    void setUp() {
        DataStorage.reset();
        this.project = new Project(new ArrayList<>(), "Test Project", new ArrayList<>());
    }

    @Test
    void testAddNullAnimal() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.project.addAnimal(null);
        });
    }

    @Test
    void testAddValidAnimal() {
        var animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        this.project.addAnimal(animal);

        assertAll(() -> {
            assertTrue(this.project.containsAnimal(animal));
            assertEquals(1, this.project.getAnimals().size());
            assertEquals(animal, this.project.getAnimals().getFirst());
            assertTrue(this.project.containsAnimalType(AnimalClass.BIRD));
        });
    }

    @Test
    void testAddExisingAnimal() {
        var animals = new LinkedList<Animal>();
        var animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        animals.add(animal);
        for (var currAnimal : animals) {
            this.project.addAnimal(currAnimal);
        }

        assertThrows(IllegalArgumentException.class, () -> {
            project.addAnimal(animal);
        });
    }
}