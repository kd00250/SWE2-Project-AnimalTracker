package edu.westga.cs3211.animaltracker.model.server.request.data.getprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestConstructor {
    @Test
    void testConstructorAndGetterWithValidToken() {
        GetProjectRequest request = new GetProjectRequest("abc123");

        assertEquals("abc123", request.getToken());
    }
}
