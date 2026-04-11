package edu.westga.cs3211.animaltracker.model.server.request.data.addprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.CREATE_PROJECT_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

public class TestToJson {

    @Test
    void testToJsonShouldReturnCorrectJsonWithAllFieldsCurrentlyIncluded() {
        // Arrange
        String projectName = "Wildlife Study";
        Collection<String> usernames = Arrays.asList("alice", "bob");
        Collection<Integer> animalIds = Arrays.asList(101, 202);
        String token = "abc123";

        AddProjectRequest request = new AddProjectRequest(projectName, usernames, animalIds, token);

        // Act
        JSONObject result = request.toJson();

        // Assert
        assertEquals(CREATE_PROJECT_REQUEST, result.getString("action"));
        assertEquals(token, result.getString("token"));
        assertEquals(projectName, result.getString("project name"));

        JSONArray usersArray = result.getJSONArray("users");
        assertEquals(2, usersArray.length());
        assertEquals("alice", usersArray.getString(0));
        assertEquals("bob", usersArray.getString(1));
    }

    @Test
    void testToJsonShouldReturnEmptyUsersArrayWhenNoScientistsProvided() {
        // Arrange
        String projectName = "Empty Scientists Project";
        Collection<String> usernames = List.of();
        Collection<Integer> animalIds = Arrays.asList(1, 2);
        String token = "token123";

        AddProjectRequest request = new AddProjectRequest(projectName, usernames, animalIds, token);

        // Act
        JSONObject result = request.toJson();

        // Assert
        assertEquals(CREATE_PROJECT_REQUEST, result.getString("action"));
        assertEquals(token, result.getString("token"));
        assertEquals(projectName, result.getString("project name"));

        JSONArray usersArray = result.getJSONArray("users");
        assertEquals(0, usersArray.length());
    }

    @Test
    void testToJsonShouldNotIncludeAnimalIdsSinceMethodDoesNotAddThem() {
        // Arrange
        String projectName = "Animal Project";
        Collection<String> usernames = List.of("scientist1");
        Collection<Integer> animalIds = Arrays.asList(10, 20, 30);
        String token = "myToken";

        AddProjectRequest request = new AddProjectRequest(projectName, usernames, animalIds, token);

        // Act
        JSONObject result = request.toJson();

        // Assert
        assertFalse(result.has("animalIds"));
        assertFalse(result.has("animals"));
    }
}
