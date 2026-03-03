package edu.westga.cs3211.animaltracker.model.server.request;

import java.util.Random;

/**
 * The response abstract class.
 */
public abstract class Response {
    private long responseId;

    /**
     * Initializes a new response with a response id.
     *
     * @param responseId the response id.
     */
    public Response(long responseId) {
        this.responseId = responseId;
    }

    /**
     * Default constructor to test with seeded rng.
     *
     * @param random the random number generator
     */
    public Response(Random random) {
        this.responseId = random.nextLong();
    }

    /**
     * Initializes a responseId with a randomized id.
     */
    public Response() {
        this.responseId = new Random().nextLong();
    }

    /**
     * Gets the response id.
     *
     * @return the id
     */
    public long getResponseId() {
        return this.responseId;
    }
}
