package edu.westga.cs3211.animaltracker.model.server.request;

public class InvalidResponseException extends RuntimeException {
    public InvalidResponseException(String message) {
        super(message);
    }
}
