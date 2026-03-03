package edu.westga.cs3211.animaltracker.model.server.request.request;

import edu.westga.cs3211.animaltracker.model.server.request.PredictableRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest {
    @Test
    void testValidConstructor() {
        var request = new FakeRequest(new PredictableRandom());
        assertEquals(PredictableRandom.longValue, request.getRequestId(), 0.0001);
    }

}
