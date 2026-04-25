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
        LocalDateTime testTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, testTime, "Saw it near a tree.", "Bob");

        assertAll(
                () -> assertEquals(this.testAnimal.getTagID(), sighting.getAnimalTagID()),
                () -> assertEquals("Forest", sighting.getLocation()),
                () -> assertEquals(45.0, sighting.getLatitude()),
                () -> assertEquals(90.0, sighting.getLongitude()),
                () -> assertEquals(testTime, sighting.getTime()),
                () -> assertEquals("Saw it near a tree.", sighting.getNotes()),
                () -> assertEquals("Bob", sighting.getUsername())
        );
    }

    // Animal ID Testing

    @Test
    void testInvalidSightingNegativeAnimalTagID() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(-1, "Forest", 45.0, 90.0, testTime, "Saw it near a tree.", "Bob");
        });
    }

    // Location Testing

    @Test
    void testInvalidSightingNullLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), null, 45.0, 90.0, testTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testInvalidSightingEmptyLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "", 45.0, 90.0, testTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testInvalidSightingWhitespaceLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "   ", 45.0, 90.0, testTime, "Saw it near a tree.", "Bob");
        });
    }

    // Latitude Boundary Testing

    @Test
    void testInvalidSightingLatitudeTooLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "Forest", -90.1, 0, testTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testInvalidSightingLatitudeTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "Forest", 90.1, 0, testTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testValidSightingLatitudeBoundaryMin() {
        LocalDateTime testTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", -90.0, 0, testTime, "Saw it near a tree.", "Bob");
        assertEquals(-90.0, sighting.getLatitude());
    }

    @Test
    void testValidSightingLatitudeBoundaryMax() {
        LocalDateTime testTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", 90.0, 0, testTime, "Saw it near a tree.", "Bob");
        assertEquals(90.0, sighting.getLatitude());
    }

    // Longitude Boundary Testing

    @Test
    void testInvalidSightingLongitudeTooLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "Forest", 0, -180.1, testTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testInvalidSightingLongitudeTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "Forest", 0, 180.1, testTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testValidSightingLongitudeBoundaryMin() {
        LocalDateTime testTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", 0, -180.0, testTime, "Saw it near a tree.", "Bob");
        assertEquals(-180.0, sighting.getLongitude());
    }

    @Test
    void testValidSightingLongitudeBoundaryMax() {
        LocalDateTime testTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", 0, 180.0, testTime, "Saw it near a tree.", "Bob");
        assertEquals(180.0, sighting.getLongitude());
    }

    // Time Testing

    @Test
    void testInvalidSightingFutureTime() {
        LocalDateTime futureTime = LocalDateTime.now().plusHours(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, futureTime, "Saw it near a tree.", "Bob");
        });
    }

    @Test
    void testInvalidSightingNullTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, null, "Saw it near a tree.", "Bob");
        });
    }

    // Notes Testing

    @Test
    void testInvalidSightingNullNotes() {
        LocalDateTime pastTime = LocalDateTime.now().minusHours(2);
        assertThrows(IllegalArgumentException.class, () -> {
            new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, pastTime, null, "Bob");
        });
    }

    @Test
    void testInvalidSightingEmptyNotes() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, testTime, "", "Bob");
        });
    }

    @Test
    void testInvalidSightingWhitespaceNotes() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime testTime = LocalDateTime.now().minusHours(2);
            new Sighting(this.testAnimal.getTagID(), "Forest", 45.0, 90.0, testTime, "   ", "Bob");
        });
    }

    // ToString Testing

    @Test
    void testToString() {
        LocalDateTime testTime = LocalDateTime.now().minusHours(2);
        Sighting sighting = new Sighting(this.testAnimal.getTagID(), "Forest", 12.345, -67.89, testTime, "Saw it near a tree.", "Bob");
        System.out.printf(sighting.toString());
        String expectedFormat = "Sighting: " + this.testAnimal.getTagID() + " at Forest (12.34500, -67.89000) with Notes: " + sighting.getNotes() + " by Bob at " + sighting.getTime();

        assertAll(
                () -> assertTrue(sighting.toString().contains("Forest")),
                () -> assertTrue(sighting.toString().contains("12.34500")),
                () -> assertTrue(sighting.toString().contains("-67.89000")),
                () -> assertTrue(sighting.toString().contains("Saw it near a tree.")),
                () -> assertTrue(sighting.toString().contains("Bob")),
                () -> assertEquals(expectedFormat, sighting.toString())
        );
    }
}