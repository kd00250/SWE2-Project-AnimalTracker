package edu.westga.cs3211.animaltracker.model.server.request.data.addsightingrequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;

class TestValidateRequest {

    @Test
    void validateRequestShouldNotThrowWhenRequestIsValid() {
        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal.getTagID(),
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees",
                null);

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void validateRequestShouldThrowWhenTokenIsNull() {
        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal.getTagID(),
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees",
                null);

        AddSightingRequest request = new AddSightingRequest(null, sighting);

        InvalidRequestException exception =
                assertThrows(InvalidRequestException.class, request::validateRequest);

        assertEquals("Token is null", exception.getMessage());
    }

    @Test
    void validateRequestShouldThrowWhenSightingIsNull() {
        AddSightingRequest request = new AddSightingRequest("token123", null);

        InvalidRequestException exception =
                assertThrows(InvalidRequestException.class, request::validateRequest);

        assertEquals("Sighting is null", exception.getMessage());
    }
}
