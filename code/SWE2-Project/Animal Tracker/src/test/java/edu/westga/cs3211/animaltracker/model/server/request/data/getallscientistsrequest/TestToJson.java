package edu.westga.cs3211.animaltracker.model.server.request.data.getallscientistsrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetAllScientistsRequests;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_SCIENTISTS_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToJson {

    @Test
    void shouldReturnCorrectJson() {
        String token = "abc123";
        GetAllScientistsRequests request = new GetAllScientistsRequests(token);

        JSONObject json = request.toJson();

        assertEquals(GET_SCIENTISTS_REQUEST, json.getString("action"));
        assertEquals(token, json.getString("token"));
    }
}
