package edu.westga.cs3211.animaltracker.model.server.request.response;

import edu.westga.cs3211.animaltracker.model.server.request.PredictableRandom;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTest {
    @Test
    void testValidConstructor() {
        var random = new PredictableRandom();
        var response = new FakeResponse(random);
        assertEquals(PredictableRandom.longValue, response.getResponseId());
    }

    @Test
    void testWithRandomValue() {
        var response = new FakeResponse(1L);
        assertEquals(1L, response.getResponseId());
    }
}
