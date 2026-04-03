package edu.westga.cs3211.animaltracker.model.server.request.data.getprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_PROJECTS_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class TestToJson {
    @Test
    void testToJsonWithValidTokenReturnsCorrectJson() {
        GetProjectRequest request = new GetProjectRequest("abc123");

        JSONObject json = request.toJson();

        assertEquals(GET_PROJECTS_REQUEST, json.get("action"));
        assertEquals("abc123", json.getString("token"));
    }
}
