package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_SCIENTISTS_REQUEST;

/**
 * the get all scientists requests.
 */
public class GetAllScientistsRequests extends Request {
    private String token;

    /**
     * creates a new instance of get all scientists request.
     * @param token the token
     */
    public GetAllScientistsRequests(String token) {
        this.token = token;
    }

    /**
     * gets the token.
     * @return the token
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
            throw new InvalidRequestException("Token is null");
        }
    }

    /**
     * creates the json string to be sent.
     * @return the json string to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", GET_SCIENTISTS_REQUEST);
        json.put("token", this.getToken());
        return json;
    }
}
