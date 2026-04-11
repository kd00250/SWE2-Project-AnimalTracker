package edu.westga.cs3211.animaltracker.model.server.request;

/**
 * The Invalid Response Exception used when an invalid response is received.
 */
public class InvalidResponseException extends RuntimeException {
    /**
     * Used to initialize an invalid response exception with a given message.
     * @param message the message
     */
    public InvalidResponseException(String message) {
        super(message);
    }
}
