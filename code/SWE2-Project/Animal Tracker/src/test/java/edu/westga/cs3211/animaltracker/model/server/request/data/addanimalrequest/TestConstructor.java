package edu.westga.cs3211.animaltracker.model.server.request.data.addanimalrequest;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddAnimalRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConstructor {

    @Test
    void testConstructorShouldSetFieldsCorrectly() {
        String token = "abc123";
        String projectName = "Project A";
        int projectID = 10;
        Animal animal = new Animal(AnimalClass.values()[0], 12.5, 30.0, 18.0, 101, "Test animal");

        AddAnimalRequest request = new AddAnimalRequest(token, projectName, projectID, animal);

        assertEquals(token, request.getToken());
        assertEquals(projectName, request.getProjectName());
        assertEquals(projectID, request.getProjectID());
        assertEquals(animal, request.getAnimal());
    }
}
