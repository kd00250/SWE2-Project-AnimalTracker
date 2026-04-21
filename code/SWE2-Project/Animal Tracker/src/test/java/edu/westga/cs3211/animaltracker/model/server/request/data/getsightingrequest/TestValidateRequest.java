package edu.westga.cs3211.animaltracker.model.server.request.data.getsightingrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetSightingRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_SIGHTINGS_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

public class TestValidateRequest {

    @Test
    void testToJsonContainsCorrectAction() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        JSONObject json = request.toJson();

        assertEquals(GET_SIGHTINGS_REQUEST, json.getString("action"));
    }

    @Test
    void testToJsonContainsCorrectToken() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        JSONObject json = request.toJson();

        assertEquals("abc123", json.getString("token"));
    }

    @Test
    void testToJsonContainsCorrectTagID() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        JSONObject json = request.toJson();

        assertEquals(42, json.getInt("tagID"));
    }

    @Test
    void testToJsonCreatesExpectedJsonObject() {
        GetSightingRequest request = new GetSightingRequest("abc123", 42);

        JSONObject expected = new JSONObject();
        expected.put("action", GET_SIGHTINGS_REQUEST);
        expected.put("token", "abc123");
        expected.put("tagID", 42);

        assertEquals(expected.toString(), request.toJson().toString());
    }
}
