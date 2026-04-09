package edu.westga.cs3211.animaltracker.model.server.request.data.getsingleprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.GetSingleProjectRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_PROJECT_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToJson {

    @Test
    void shouldReturnCorrectJson() {
        String token = "abc123";
        String projectName = "Project A";
        int projectID = 10;

        GetSingleProjectRequest request =
                new GetSingleProjectRequest(token, projectName, projectID);

        JSONObject json = request.toJson();

        assertEquals(token, request.getToken());
        assertEquals(projectName, request.getProjectName());
        assertEquals(GET_PROJECT_REQUEST, json.getString("action"));
        assertEquals(token, json.getString("token"));
        assertEquals(projectName, json.getString("project name"));
        assertEquals(projectID, json.getInt("project id"));
    }
}
