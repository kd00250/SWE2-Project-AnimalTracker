package edu.westga.cs3211.animaltracker.model.AnimalModel;

import static org.junit.jupiter.api.Assertions.*;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import org.junit.jupiter.api.Test;

/**
 * The test Constructor Class.
 */
public class ConstructorTest {

    @Test
    void testValidConstructor() {
        Animal animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        assertEquals(AnimalClass.BIRD, animal.getAnimalClass());
        assertEquals(11.0, animal.getHeight());
        assertEquals(15.0, animal.getWeight());
        assertEquals(17.0, animal.getLength());
        assertEquals(122345, animal.getTagID());
        assertEquals("", animal.getDescription());
    }

    @Test
    void testNullAnimalClass() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(null, 11.0, 15.0, 17.0, 122345, ""));
    }

    @Test
    void testLessThanZeroHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(AnimalClass.BIRD, 0.0, 15.0, 17.0, 122345, ""));
    }

    @Test
    void testZeroWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(AnimalClass.BIRD, 12.0, 0.0, 17.0, 122345, ""));
    }

    @Test
    void testZeroLength() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(AnimalClass.BIRD, 12.0, 10.0, 0.0, 122345, ""));
    }

    @Test
    void testZeroTagID() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(AnimalClass.BIRD, 12.0, 10.0, 17.0, 0, ""));
    }

    @Test
    void testNegativeTagID() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(AnimalClass.BIRD, 12.0, 10.0, 17.0, -21, ""));
    }

    @Test
    void testNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Animal(AnimalClass.BIRD, 12.0, 10.0, 17.0, 122345, null));
    }
}
