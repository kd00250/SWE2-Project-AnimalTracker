package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGetAnimalById {
    @Test
    void testGetAnimalByIdBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataStorage.getAnimalById(-1);
        });
    }

    @Test
    void testGetAnimalMatches() {
        Animal animal1 = new Animal(AnimalClass.BIRD, 14, 24, 14, 14, "Bird");
        Animal animal2 = new Animal(AnimalClass.MAMMAL, 16, 24, 14, 11, "Cat");
        DataStorage.getAnimals().clear();
        DataStorage.getAnimals().put(animal1.getId(), animal1);
        DataStorage.getAnimals().put(animal2.getId(), animal2);
        var found = DataStorage.getAnimalById(animal1.getId());
        assertEquals(animal1, found);
    }

    @Test
    void testGetAnimalDoesNotMatch() {
        Animal animal1 = new Animal(AnimalClass.BIRD, 14, 24, 14, 14, "Bird");
        Animal animal2 = new Animal(AnimalClass.MAMMAL, 16, 24, 14, 11, "Cat");
        DataStorage.getAnimals().clear();
        DataStorage.getAnimals().put(animal1.getId(), animal1);
        DataStorage.getAnimals().put(animal2.getId(), animal2);
        var found = DataStorage.getAnimalById(Integer.MAX_VALUE);
        assertNull(found);
    }
}
