package edu.westga.cs3211.animaltracker.model.server.request.data.addsightingrequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;

public class TestConstructor {

    @Test
    void constructorShouldStoreTokenCorrectly() {
        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal.getTagID(),
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees");

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        assertEquals("token123", request.getToken());
    }

    @Test
    void constructorShouldStoreSightingCorrectly() {
        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal.getTagID(),
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees");

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        assertSame(sighting, request.getSighting());
    }
}
