package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_PROJECTS_REQUEST;

/**
 * the get project request class.
 */
public class GetProjectRequest extends Request {
    private String token;

    /**
     * creates a new instance of getProjectRequest.
     * @param token the token
     */
    public GetProjectRequest(String token) {
        this.token = token;
    }

    /**
     * gets the token from the request.
     * @return the token from the request
     */
    public String getToken() {
        return this.token;
    }

    /**
     * validates the request to see if.
     */
    @Override
    public void validateRequest() {
        if (this.token == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
    }

    /**
     * creates the json string to be sent.
     * @return the json string to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", GET_PROJECTS_REQUEST);
        json.put("token", this.getToken());
        return json;
    }
}
