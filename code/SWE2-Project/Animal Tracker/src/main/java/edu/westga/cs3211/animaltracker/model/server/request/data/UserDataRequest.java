package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_PROJECTS_REQUEST;
import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.USER_ROLE_REQUEST;

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", USER_ROLE_REQUEST);
        json.put("token", this.getToken());
        return json;
    }
}
