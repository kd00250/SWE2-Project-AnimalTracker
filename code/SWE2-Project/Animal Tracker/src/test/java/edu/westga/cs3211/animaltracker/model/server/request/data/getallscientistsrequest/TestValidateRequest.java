package edu.westga.cs3211.animaltracker.model.server.request.data.getallscientistsrequest;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetAllScientistsRequests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateRequest {

    @Test
    void shouldNotThrowWhenTokenIsValid() {
        GetAllScientistsRequests request = new GetAllScientistsRequests("abc123");

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void shouldThrowWhenTokenIsNull() {
        GetAllScientistsRequests request = new GetAllScientistsRequests(null);

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                request::validateRequest
        );

        assertEquals("Token is null", exception.getMessage());
    }
}
