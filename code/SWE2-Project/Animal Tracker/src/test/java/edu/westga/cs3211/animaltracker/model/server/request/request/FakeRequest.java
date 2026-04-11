package edu.westga.cs3211.animaltracker.model.server.request.request;

import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import java.util.Random;

/**
 * The FakeRequest class.
 */
public class FakeRequest extends Request {
    /**
     * Instantiates a fake request.
     *
     * @param random the randomizer
     */
    public FakeRequest(Random random) {
        super(random);
    }

    @Override
    public void validateRequest() {
    }

    /**
     * This was for testing purposes.
     * @return null because it isn't needed
     */
    @Override
    public JSONObject toJson() {
        return null;
    }
}
