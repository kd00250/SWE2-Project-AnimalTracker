package edu.westga.cs3211.animaltracker.model.server.request.data.getsightingrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetSightingRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestToJson {

    @Test
    void testValidateRequestWithValidTokenAndTagIDDoesNotThrowException() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testValidateRequestWithNullTokenThrowsException() {
        GetSightingRequest request = new GetSightingRequest(null, 42);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, request::validateRequest);

        assertEquals("Token cannot be null", exception.getMessage());
    }

    @Test
    void testValidateRequestWithZeroTagIDThrowsException() {
        GetSightingRequest request = new GetSightingRequest("abc123", 0);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, request::validateRequest);

        assertEquals("TagID cannot be zero", exception.getMessage());
    }
}
