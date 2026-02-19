package edu.westga.cs3211.animaltracker.model.login.request;

/**
 * The invalid request exception class.
 */
public class InvalidRequestException extends RuntimeException {
    /**
     * Creates a new invalid request exception.
     *
     * @param message the message
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}
