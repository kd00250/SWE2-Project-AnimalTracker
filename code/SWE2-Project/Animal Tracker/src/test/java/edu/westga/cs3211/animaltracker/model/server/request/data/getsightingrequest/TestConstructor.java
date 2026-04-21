package edu.westga.cs3211.animaltracker.model.server.request.data.getsightingrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetSightingRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestConstructor {

    @Test
    void testConstructorSetsTokenCorrectly() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        assertEquals("abc123", request.getToken());
    }

    @Test
    void testConstructorSetsTagIDCorrectly() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        assertEquals(42, request.getTagID());
    }

    @Test
    void testConstructorWithNullTokenStoresNullToken() {
        GetSightingRequest request = new GetSightingRequest(null, 42);

        assertNull(request.getToken());
    }

    @Test
    void testConstructorWithZeroTagIDStoresZeroTagID() {
        GetSightingRequest request = new GetSightingRequest("abc123", 0);

        assertEquals(0, request.getTagID());
    }
}
