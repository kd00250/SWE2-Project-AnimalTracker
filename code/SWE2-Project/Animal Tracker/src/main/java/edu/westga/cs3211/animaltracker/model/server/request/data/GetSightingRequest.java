package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_SIGHTINGS_REQUEST;

/**
 * the get sighting request class.
 */
public class GetSightingRequest extends Request {
    private String token;
    private int tagID;

    /**
     * creates a new instance of the get sighting request class.
     * @param token the token
     * @param tagID the tagID of the animal to get the requests
     */
    public GetSightingRequest(String token, int tagID) {
        this.tagID = tagID;
        this.token = token;
    }

    /**
     * gets the token of the user.
     * @return the token of the user
     */
    public String getToken() {
        return this.token;
    }

    /**
     * gets the tagID of the user.
     * @return the tagID of the user
     */
    public int getTagID() {
        return this.tagID;
    }

    /**
     * Validates that the request is valid to be sent.
     */
    @Override
    public void validateRequest() {
        if (this.token == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        if (this.tagID == 0) {
            throw new IllegalArgumentException("TagID cannot be zero");
        }
    }

    /**
     * converts the request to json to be sent.
     *
     * @return the json object to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("action", GET_SIGHTINGS_REQUEST);
        json.put("token", this.getToken());
        json.put("tagID", this.getTagID());
        return json;
    }
}
