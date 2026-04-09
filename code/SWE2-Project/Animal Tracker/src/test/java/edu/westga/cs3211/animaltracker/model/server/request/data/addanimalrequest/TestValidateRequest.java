package edu.westga.cs3211.animaltracker.model.server.request.data.addanimalrequest;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddAnimalRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateRequest {

    @Test
    void testValidateRequestShouldNotThrowWhenTokenAndProjectNameAreValid() {
        Animal animal = new Animal(AnimalClass.values()[0], 12.5, 30.0, 18.0, 101, "Test animal");
        AddAnimalRequest request = new AddAnimalRequest("token123", "Project A", 10, animal);

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testValidateRequestShouldThrowWhenTokenIsNull() {
        Animal animal = new Animal(AnimalClass.values()[0], 12.5, 30.0, 18.0, 101, "Test animal");
        AddAnimalRequest request = new AddAnimalRequest(null, "Project A", 10, animal);

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                request::validateRequest
        );
        assertEquals("Token is null", exception.getMessage());
    }

    @Test
    void testValidateRequestShouldThrowWhenProjectNameIsNull() {
        Animal animal = new Animal(AnimalClass.values()[0], 12.5, 30.0, 18.0, 101, "Test animal");
        AddAnimalRequest request = new AddAnimalRequest("token123", null, 10, animal);

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                request::validateRequest
        );
        assertEquals("Project name is null", exception.getMessage());
    }
}
