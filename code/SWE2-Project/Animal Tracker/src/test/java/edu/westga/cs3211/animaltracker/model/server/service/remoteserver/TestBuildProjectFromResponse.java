package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestBuildProjectFromResponse {

    @Test
    void shouldThrowWhenResponseIsNull() throws Exception {
        RemoteServer server = new RemoteServer();

        Method method = RemoteServer.class.getDeclaredMethod(
                "buildProjectFromResponse",
                JSONObject.class,
                String.class,
                int.class
        );
        method.setAccessible(true);

        Exception exception = assertThrows(Exception.class, () ->
                method.invoke(server, null, "Project A", 1)
        );

        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("Response cannot be null", exception.getCause().getMessage());

        server.close();
    }

    @Test
    void shouldBuildProjectWhenNoAnimals() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject response = new JSONObject();
        response.put("animals", new JSONArray());

        Method method = RemoteServer.class.getDeclaredMethod(
                "buildProjectFromResponse",
                JSONObject.class,
                String.class,
                int.class
        );
        method.setAccessible(true);

        Project result = (Project) method.invoke(server, response, "Project A", 1);

        assertEquals("Project A", result.getName());
        assertEquals(1, result.getId());
        assertTrue(result.getAnimals().isEmpty());

        server.close();
    }

    @Test
    void shouldBuildProjectWithValidAnimal() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject animal = new JSONObject();
        animal.put("Class", AnimalClass.values()[0].name());
        animal.put("Height", 10.0);
        animal.put("Weight", 20.0);
        animal.put("Length", 30.0);
        animal.put("TagID", 1);
        animal.put("Description", "Valid animal");

        JSONArray animals = new JSONArray();
        animals.put(animal);

        JSONObject response = new JSONObject();
        response.put("animals", animals);

        Method method = RemoteServer.class.getDeclaredMethod(
                "buildProjectFromResponse",
                JSONObject.class,
                String.class,
                int.class
        );
        method.setAccessible(true);

        Project result = (Project) method.invoke(server, response, "Project A", 1);

        assertEquals(1, result.getAnimals().size());

        server.close();
    }
}
