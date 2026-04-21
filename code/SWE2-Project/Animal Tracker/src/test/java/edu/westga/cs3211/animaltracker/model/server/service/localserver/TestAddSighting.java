package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;

class TestAddSighting {

    @Test
    void addSightingShouldReturnTrueWhenGivenValidRequest() {
        LocalServer server = new LocalServer();

        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal.getTagID(),
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees");

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        boolean result = server.addSighting(request);

        assertTrue(result);
    }

    @Test
    void addSightingShouldReturnTrueWhenRequestIsNull() {
        LocalServer server = new LocalServer();

        boolean result = server.addSighting(null);

        assertTrue(result);
    }
}
