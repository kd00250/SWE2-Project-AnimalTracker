package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetSightingRequest;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestGetSightings {

    @Test
    void testGetSightingsReturnsNullForValidRequest() {
        LocalServer server = new LocalServer();
        GetSightingRequest request = new GetSightingRequest("abc123", 1);

        List<Sighting> result = server.getSightings(request);

        assertNull(result);
    }

    @Test
    void testGetSightingsReturnsNullForNullRequest() {
        LocalServer server = new LocalServer();

        List<Sighting> result = server.getSightings(null);

        assertNull(result);
    }
}
