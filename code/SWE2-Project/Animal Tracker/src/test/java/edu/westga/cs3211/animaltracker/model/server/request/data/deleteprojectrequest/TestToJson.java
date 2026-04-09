package edu.westga.cs3211.animaltracker.model.server.request.data.deleteprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.DeleteProjectRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.DELETE_PROJECT_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToJson {

    @Test
    void testToJsonShouldReturnCorrectJsonWhenTokenIsProvided() {

        String token = "abc123";
        int projectId = 10;
        DeleteProjectRequest request = new DeleteProjectRequest(token, projectId);

        JSONObject json = request.toJson();

        assertEquals(DELETE_PROJECT_REQUEST, json.getString("action"));
        assertEquals(token, json.getString("token"));
        assertEquals(projectId, json.getInt("project id"));
    }
}
