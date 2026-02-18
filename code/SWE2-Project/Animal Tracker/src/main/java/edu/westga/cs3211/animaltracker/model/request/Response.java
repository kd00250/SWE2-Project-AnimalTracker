package edu.westga.cs3211.animaltracker.model.request;

import java.util.Random;

public abstract class Response {
    private long responseId;

    public Response(long responseId) {
        this.responseId = responseId;
    }

    public Response() {
        this.responseId = new Random().nextLong();
    }

    public long getResponseId() {
        return responseId;
    }
}
