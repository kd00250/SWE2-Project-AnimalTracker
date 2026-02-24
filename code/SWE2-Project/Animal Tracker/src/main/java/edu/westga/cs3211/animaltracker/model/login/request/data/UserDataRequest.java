package edu.westga.cs3211.animaltracker.model.login.request.data;

import edu.westga.cs3211.animaltracker.model.login.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.login.request.Request;

public final class UserDataRequest extends Request {
    private String token;

    public UserDataRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public void validateRequest() {
        if (token == null) {
            throw new InvalidRequestException("Token is null");
        }
        if (token.isEmpty()) {
            throw new InvalidRequestException("Token is empty");
        }
    }
}
