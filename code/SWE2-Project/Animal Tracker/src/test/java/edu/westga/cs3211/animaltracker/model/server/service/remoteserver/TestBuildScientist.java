package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBuildScientist {

    @Test
    void shouldThrowWhenJsonIsNull() throws Exception {
        RemoteServer server = new RemoteServer();

        Method method = RemoteServer.class.getDeclaredMethod("buildScientistsFromJson", JSONObject.class);
        method.setAccessible(true);

        Exception exception = assertThrows(Exception.class, () ->
                method.invoke(server, new Object[]{null})
        );

        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("JSON cannot be null", exception.getCause().getMessage());

        server.close();
    }

    @Test
    void shouldReturnScientistsFromJson() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject user1 = new JSONObject();
        user1.put("username", "alice");
        user1.put("password", "pass1");

        JSONObject user2 = new JSONObject();
        user2.put("username", "bob");
        user2.put("password", "pass2");

        JSONArray users = new JSONArray();
        users.put(user1);
        users.put(user2);

        JSONObject json = new JSONObject();
        json.put("users", users);

        Method method = RemoteServer.class.getDeclaredMethod("buildScientistsFromJson", JSONObject.class);
        method.setAccessible(true);

        List<User> result = (List<User>) method.invoke(server, json);

        assertEquals(2, result.size());

        server.close();
    }

    @Test
    void shouldReturnEmptyListWhenNoUsers() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("users", new JSONArray());

        Method method = RemoteServer.class.getDeclaredMethod("buildScientistsFromJson", JSONObject.class);
        method.setAccessible(true);

        List<User> result = (List<User>) method.invoke(server, json);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        server.close();
    }
}
