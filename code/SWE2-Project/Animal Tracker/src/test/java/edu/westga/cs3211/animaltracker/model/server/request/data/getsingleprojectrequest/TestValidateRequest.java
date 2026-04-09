package edu.westga.cs3211.animaltracker.model.server.request.data.getsingleprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetSingleProjectRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateRequest {

    @Test
    void shouldNotThrowWhenTokenAndProjectNameAreValid() {
        GetSingleProjectRequest request =
                new GetSingleProjectRequest("abc123", "Project A", 10);

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void shouldThrowWhenTokenIsNull() {
        GetSingleProjectRequest request =
                new GetSingleProjectRequest(null, "Project A", 10);

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                request::validateRequest
        );

        assertEquals("Token is null", exception.getMessage());
    }

    @Test
    void shouldThrowWhenProjectNameIsNull() {
        GetSingleProjectRequest request =
                new GetSingleProjectRequest("abc123", null, 10);

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                request::validateRequest
        );

        assertEquals("Project name is null", exception.getMessage());
    }
}
