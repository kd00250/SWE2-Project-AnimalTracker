package edu.westga.cs3211.animaltracker.model.server.request.response;

import edu.westga.cs3211.animaltracker.model.server.request.Response;

import java.util.Random;

public class FakeResponse extends Response {
    public FakeResponse(Random randomizer) {
        super(randomizer);
    }

    public FakeResponse(long responseId) {
        super(responseId);
    }
}
