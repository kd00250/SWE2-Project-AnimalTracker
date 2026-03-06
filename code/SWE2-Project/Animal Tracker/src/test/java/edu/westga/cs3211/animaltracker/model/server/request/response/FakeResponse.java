package edu.westga.cs3211.animaltracker.model.server.request.response;

import edu.westga.cs3211.animaltracker.model.server.request.Response;

import java.util.Random;

/**
 * The FakeResponse class.
 */
public class FakeResponse extends Response {
    /**
     * Instantiates a fake response.
     * @param randomizer the randomizer
     */
    public FakeResponse(Random randomizer) {
        super(randomizer);
    }

    /**
     * Instantiates a fake response.
     * @param responseId the response id
     */
    public FakeResponse(long responseId) {
        super(responseId);
    }
}
