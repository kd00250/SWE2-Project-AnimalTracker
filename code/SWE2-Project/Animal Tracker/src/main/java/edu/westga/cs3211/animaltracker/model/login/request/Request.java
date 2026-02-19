package edu.westga.cs3211.animaltracker.model.login.request;

import java.util.Random;

/**
 * The abstract request class.
 */
public abstract class Request {
    private long requestId;

    /**
     * Initializes a new request with a random id.
     */
    public Request() {
        this.requestId = new Random().nextLong();
    }

    /**
     * Initializes a new request with a given randomizer, used only for testing.
     *
     * @param random the randomizer
     */
    public Request(Random random) {
        this.requestId = random.nextLong();
    }

    /**
     * Gets the id for this request.
     *
     * @return the id
     */
    public long getRequestId() {
        return this.requestId;
    }

    /**
     * Validates that the request is valid to be sent.
     */
    public abstract void validateRequest();
}
