package edu.westga.cs3211.animaltracker.model.server.request.data.deleteprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.DeleteProjectRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateRequest {

    @Test
    void testValidateRequestShouldNotThrowWhenProjectIdIsZero() {
        DeleteProjectRequest request = new DeleteProjectRequest(0);

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testValidateRequestShouldNotThrowWhenProjectIdIsPositive() {
        DeleteProjectRequest request = new DeleteProjectRequest("aaa", 5);

        assertEquals("aaa", request.getToken());
        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testValidateRequestShouldThrowWhenProjectIdIsNegative() {
        DeleteProjectRequest request = new DeleteProjectRequest(-1);

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                request::validateRequest
        );

        assertEquals("Project id is null", exception.getMessage());
    }
}
