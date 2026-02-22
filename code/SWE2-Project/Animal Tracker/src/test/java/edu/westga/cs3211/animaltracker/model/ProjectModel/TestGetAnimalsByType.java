package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGetAnimalsByType {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testGetAnimalsWithNullAnimalClass() {
        var project = new Project();
        assertThrows(IllegalArgumentException.class, () -> project.getAnimalsByType(null));
    }

    @Test
    void testGetAnimalsWithNoAnimals() {
        var project = new Project();
        var result = project.getAnimalsByType(AnimalClass.MAMMAL);

        assertAll(() -> {
            assertNotNull(result);
            assertTrue(result.isEmpty());
        });
    }

    @Test
    void testGetAnimalsWithOneAnimal() {
        var project = new Project();
        var animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        project.addAnimal(animal);
        var result = project.getAnimalsByType(AnimalClass.BIRD);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(animal, result.getFirst());
        });
    }

    @Test
    void testGetAnimalsWithMultipleAnimalOfSameClass() {
        var project = new Project();
        var animal1 = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        var animal2 = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122346, "");
        var animal3 = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122347, "");
        project.addAnimal(animal1);
        project.addAnimal(animal2);
        project.addAnimal(animal3);
        var result = project.getAnimalsByType(AnimalClass.BIRD);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(3, result.size());
            assertEquals(animal1, result.get(0));
            assertEquals(animal2, result.get(1));
            assertEquals(animal3, result.get(2));
        });
    }

    @Test
    void testGetAnimalsWithMultipleAnimalWithDifferentClasses() {
        var project = new Project();
        var animal1 = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        var animal2 = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122346, "");
        var animal3 = new Animal(AnimalClass.MAMMAL, 11.0, 15.0, 17.0, 122347, "");
        project.addAnimal(animal1);
        project.addAnimal(animal2);
        project.addAnimal(animal3);
        var result = project.getAnimalsByType(AnimalClass.BIRD);

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(animal1, result.get(0));
            assertEquals(animal2, result.get(1));
        });
    }
}