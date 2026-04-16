package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.ADD_SIGHTING_REQUEST;

/**
 * the add sighting request class.
 */
public class AddSightingRequest extends Request {
    private String token;
    private Sighting sighting;

    /**
     * creates a new instance of add sighting request.
     * @param token the token
     * @param sighting the sighting
     */
    public AddSightingRequest(String token, Sighting sighting) {
        this.token = token;
        this.sighting = sighting;
    }

    /**
     * gets the token of the user.
     * @return the token of the user
     */
    public String getToken() {
        return this.token;
    }

    /**
     * gets the sighting.
     * @return the sighting that was created
     */
    public Sighting getSighting() {
        return this.sighting;
    }

    /**
     * Validates that the request is valid to be sent.
     */
    @Override
    public void validateRequest() {
        if (this.token == null) {
            throw new InvalidRequestException("Token is null");
        }
        if (this.sighting == null) {
            throw new InvalidRequestException("Sighting is null");
        }
    }

    /**
     * converts the request to Json to be sent.
     *
     * @return the json object to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", ADD_SIGHTING_REQUEST);
        json.put("token", this.getToken());
        json.put("animal", this.getSighting().getAnimal());
        json.put("location", this.getSighting().getLocation());
        json.put("latitude", this.getSighting().getLatitude());
        json.put("longitude", this.getSighting().getLongitude());
        json.put("time", this.getSighting().getTime());
        json.put("notes", this.getSighting().getNotes());

        return json;
    }
}
