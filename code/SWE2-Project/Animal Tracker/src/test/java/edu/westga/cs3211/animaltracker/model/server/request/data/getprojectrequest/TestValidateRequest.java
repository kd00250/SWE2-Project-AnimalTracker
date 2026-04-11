package edu.westga.cs3211.animaltracker.model.server.request.data.getprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TestValidateRequest {
    @Test
    void testValidateRequestWithValidTokenDoesNotThrowException() {
        GetProjectRequest request = new GetProjectRequest("abc123");

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testValidateRequestWithNullTokenThrowsException() {
        GetProjectRequest request = new GetProjectRequest(null);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, request::validateRequest);

        assertEquals("Token cannot be null", exception.getMessage());
    }
}
