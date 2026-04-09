package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestHasValidAnimalValues {

    @Test
    void shouldReturnFalseWhenJsonIsNull() throws Exception {
        RemoteServer server = new RemoteServer();

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, new Object[]{null});

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenClassIsEmpty() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", "");
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 1);
        json.put("Description", "desc");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenClassIsInvalid() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", "BAD_CLASS");
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 1);
        json.put("Description", "desc");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenHeightInvalid() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 0.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 1);
        json.put("Description", "desc");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenWeightInvalid() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 10.0);
        json.put("Weight", 0.0);
        json.put("Length", 30.0);
        json.put("TagID", 1);
        json.put("Description", "desc");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenLengthInvalid() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 0.0);
        json.put("TagID", 1);
        json.put("Description", "desc");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenTagIdInvalid() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 0);
        json.put("Description", "desc");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenDescriptionEmpty() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 1);
        json.put("Description", "   ");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertFalse(result);

        server.close();
    }

    @Test
    void shouldReturnTrueWhenAnimalIsValid() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("Class", AnimalClass.values()[0].name());
        json.put("Height", 10.0);
        json.put("Weight", 20.0);
        json.put("Length", 30.0);
        json.put("TagID", 1);
        json.put("Description", "valid");

        Method method = RemoteServer.class.getDeclaredMethod("hasValidAnimalValues", JSONObject.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(server, json);

        assertTrue(result);

        server.close();
    }
}
