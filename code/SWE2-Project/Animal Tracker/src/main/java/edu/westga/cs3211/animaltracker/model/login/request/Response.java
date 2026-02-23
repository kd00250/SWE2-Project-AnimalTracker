package edu.westga.cs3211.animaltracker.model.login.request;

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
