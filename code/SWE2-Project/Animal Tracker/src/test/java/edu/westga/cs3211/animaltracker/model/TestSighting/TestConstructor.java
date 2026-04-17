package edu.westga.cs3211.animaltracker.model.TestSighting;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Sighting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Sighting class.
 *
 * @author mrocker1
 */
public class TestConstructor {

    private Animal testAnimal;

    @BeforeEach
    void setUp() {
        // Reset storage and create a valid Animal to test with
        DataStorage.reset();
        this.testAnimal = new Animal(AnimalClass.MAMMAL, 10.5, 20.0, 15.0, 1001, "A test animal");
    }

    @Test
    void testValidSightingAllFields() {
        LocalDateTime pastTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, pastTime, "Saw it near a tree.");

        assertAll(
                () -> assertEquals(this.testAnimal.getTagID(), sighting.getAnimalTagID()),
                () -> assertEquals("Forest", sighting.getLocation()),
                () -> assertEquals(45.0, sighting.getLatitude()),
                () -> assertEquals(90.0, sighting.getLongitude()),
                () -> assertEquals(pastTime, sighting.getTime()),
                () -> assertEquals("Saw it near a tree.", sighting.getNotes())
        );
    }

    @Test
    void testValidSightingOptionalFieldsNull() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Mountain", 0.0, 0.0, null, null);

        assertAll(
                () -> assertEquals(this.testAnimal.getTagID(), sighting.getAnimalTagID()),
                () -> assertEquals("Mountain", sighting.getLocation()),
                () -> assertEquals(0.0, sighting.getLatitude()),
                () -> assertEquals(0.0, sighting.getLongitude()),
                () -> assertNull(sighting.getTime()),
                () -> assertNull(sighting.getNotes())
        );
    }

    @Test
    void testValidSightingEmptyNotesAllowed() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Plains", 10.0, -10.0, null, "");

        assertEquals("", sighting.getNotes());
    }

    @Test
    void testInvalidSightingNegativeAnimalTagID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(-1, "Forest", 45.0, 90.0, null, null);
        });
    }

    @Test
    void testInvalidSightingWithAnimalTagIDEqualZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(0, "Forest", 45.0, 90.0, null, null);
        });
    }

    @Test
    void testInvalidSightingNullLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), null, 45.0, 90.0, null, null);
        });
    }

    @Test
    void testInvalidSightingEmptyLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "", 45.0, 90.0, null, null);
        });
    }

    @Test
    void testInvalidSightingWhitespaceLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "   ", 45.0, 90.0, null, null);
        });
    }

    // Latitude Boundary Testing

    @Test
    void testInvalidSightingLatitudeTooLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Location", -90.1, 0.0, null, null);
        });
    }

    @Test
    void testInvalidSightingLatitudeTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Location", 90.1, 0.0, null, null);
        });
    }

    @Test
    void testValidSightingLatitudeBoundaryMin() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "South Pole", -90.0, 0.0, null, null);
        assertEquals(-90.0, sighting.getLatitude());
    }

    @Test
    void testValidSightingLatitudeBoundaryMax() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "North Pole", 90.0, 0.0, null, null);
        assertEquals(90.0, sighting.getLatitude());
    }

    // Longitude Boundary Testing

    @Test
    void testInvalidSightingLongitudeTooLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Location", 0.0, -180.1, null, null);
        });
    }

    @Test
    void testInvalidSightingLongitudeTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Location", 0.0, 180.1, null, null);
        });
    }

    @Test
    void testValidSightingLongitudeBoundaryMin() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Western Edge", 0.0, -180.0, null, null);
        assertEquals(-180.0, sighting.getLongitude());
    }

    @Test
    void testValidSightingLongitudeBoundaryMax() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Eastern Edge", 0.0, 180.0, null, null);
        assertEquals(180.0, sighting.getLongitude());
    }

    // Time Testing

    @Test
    void testInvalidSightingFutureTime() {
        LocalDateTime futureTime = LocalDateTime.now().plusHours(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Location", 0.0, 0.0, futureTime, null);
        });
    }

    // Notes Testing

    @Test
    void testInvalidSightingWhitespaceNotes() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Location", 0.0, 0.0, null, "    ");
        });
    }

    // ToString Testing

    @Test
    void testToString() {
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Swamp", 12.345, -67.890, null, null);
        String expectedFormat = "Sighting: Mammal " + this.testAnimal.getId() + " at Swamp (12.34500, -67.89000)";

        assertTrue(sighting.toString().contains("Swamp"));
        assertTrue(sighting.toString().contains("12.34500"));
        assertTrue(sighting.toString().contains("-67.89000"));
        assertEquals(expectedFormat, sighting.toString());
    }
}