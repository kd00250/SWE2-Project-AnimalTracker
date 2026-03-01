package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;

/**
 * The UserDataRequest class.
 */
public final class UserDataRequest extends Request {
    private final String token;

    /**
     * Instantiates a new user data request with a token.
     * @param token the token to be sent
     */
    public UserDataRequest(String token) {
        this.token = token;
    }


    /**
     * Gets the user token.
     * @return the token
     */
    public String getToken() {
        return this.token;
    }

    @Override
    public void validateRequest() {
        if (this.token == null) {
            throw new InvalidRequestException("Token is null");
        }
        if (this.token.isEmpty()) {
            throw new InvalidRequestException("Token is empty");
        }

    }
}
