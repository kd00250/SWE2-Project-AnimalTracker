package edu.westga.cs3211.animaltracker.model.login.request;

import java.util.Random;

public abstract class Request {
    private long requestId;

    public Request() {
        requestId = new Random().nextLong();
    }

    public Request(long requestId) {
        this.requestId = requestId;
    }

    public abstract void validateRequest();
}
