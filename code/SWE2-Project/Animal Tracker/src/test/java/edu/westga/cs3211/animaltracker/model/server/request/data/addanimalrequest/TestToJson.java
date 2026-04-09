package edu.westga.cs3211.animaltracker.model.server.request.data.addanimalrequest;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddAnimalRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.ADD_ANIMAL_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToJson {

    @Test
    void testToJsonShouldReturnCorrectJson() {
        String token = "abc123";
        String projectName = "Project A";
        int projectID = 10;
        Animal animal = new Animal(AnimalClass.values()[0], 12.5, 30.0, 18.0, 101, "Test animal");

        AddAnimalRequest request = new AddAnimalRequest(token, projectName, projectID, animal);

        JSONObject json = request.toJson();

        assertEquals(ADD_ANIMAL_REQUEST, json.getString("action"));
        assertEquals(token, json.getString("token"));
        assertEquals(projectName, json.getString("project name"));
        assertEquals(projectID, json.getInt("project id"));
        assertEquals(animal.getAnimalClass().name(), json.getString("Class"));
        assertEquals(animal.getHeight(), json.getDouble("Height"));
        assertEquals(animal.getWeight(), json.getDouble("Weight"));
        assertEquals(animal.getLength(), json.getDouble("Length"));
        assertEquals(animal.getTagID(), json.getInt("TagID"));
        assertEquals(animal.getDescription(), json.getString("Description"));
    }
}
