package edu.westga.cs3211.animaltracker.model.Enum.AnimalClass;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the toString AnimalClass.
 */
public class TestToString {

    @Test
    void testToString() {
        assertEquals("Mammal", AnimalClass.MAMMAL.toString());
        assertEquals("Bird", AnimalClass.BIRD.toString());
        assertEquals("Fish", AnimalClass.FISH.toString());
        assertEquals("Reptile", AnimalClass.REPTILE.toString());
        assertEquals("Amphibian", AnimalClass.AMPHIBIAN.toString());
    }
}
