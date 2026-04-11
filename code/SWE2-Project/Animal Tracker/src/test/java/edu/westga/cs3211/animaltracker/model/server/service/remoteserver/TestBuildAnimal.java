package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestBuildAnimal {

    @Test
    void shouldThrowWhenAnimalJsonIsNull() throws Exception {
        RemoteServer server = new RemoteServer();

        Method method = RemoteServer.class.getDeclaredMethod("buildAnimal", JSONObject.class);
        method.setAccessible(true);

        Exception exception = assertThrows(Exception.class, () ->
                method.invoke(server, new Object[]{null})
        );

        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("Animal JSON cannot be null", exception.getCause().getMessage());

        server.close();
    }

    @Test
    void shouldReturnAnimalFromJson() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 5);
        json.put("Description", "Test animal");

        Method method = RemoteServer.class.getDeclaredMethod("buildAnimal", JSONObject.class);
        method.setAccessible(true);

        Animal result = (Animal) method.invoke(server, json);

        assertNotNull(result);
        assertEquals(10.0, result.getHeight());
        assertEquals(20.0, result.getWeight());
        assertEquals(30.0, result.getLength());
        assertEquals(5, result.getTagID());
        assertEquals("Test animal", result.getDescription());

        server.close();
    }
}
