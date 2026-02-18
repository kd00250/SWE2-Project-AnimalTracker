package edu.westga.cs3211.animaltracker.model.login.request;

public class InvalidRequest extends RuntimeException {
    public InvalidRequest(String message) {
        super(message);
    }
}
