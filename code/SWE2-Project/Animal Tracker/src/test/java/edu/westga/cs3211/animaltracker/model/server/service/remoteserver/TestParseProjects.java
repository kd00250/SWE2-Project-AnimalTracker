package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestParseProjects {

    @Test
    void shouldReturnProjectsFromJson() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject project1 = new JSONObject();
        project1.put("id", 1);
        project1.put("name", "Project One");

        JSONObject project2 = new JSONObject();
        project2.put("id", 2);
        project2.put("name", "Project Two");

        JSONArray projects = new JSONArray();
        projects.put(project1);
        projects.put(project2);

        JSONObject json = new JSONObject();
        json.put("projects", projects);

        Method method = RemoteServer.class.getDeclaredMethod("parseProjects", JSONObject.class);
        method.setAccessible(true);

        List<Project> result = (List<Project>) method.invoke(server, json);

        assertEquals(2, result.size());

        server.close();
    }

    @Test
    void shouldReturnEmptyListWhenArrayEmpty() throws Exception {
        RemoteServer server = new RemoteServer();

        JSONObject json = new JSONObject();
        json.put("projects", new JSONArray());

        Method method = RemoteServer.class.getDeclaredMethod("parseProjects", JSONObject.class);
        method.setAccessible(true);

        List<Project> result = (List<Project>) method.invoke(server, json);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        server.close();
    }
}
