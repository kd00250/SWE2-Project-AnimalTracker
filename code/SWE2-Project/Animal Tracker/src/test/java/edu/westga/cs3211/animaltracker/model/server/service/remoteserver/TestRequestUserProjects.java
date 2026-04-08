package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestRequestUserProjects {

    private Context serverContext;
    private Socket serverSocket;
    private Thread serverThread;

    private void startServerWithResponse(String responseText) {
        this.serverContext = ZMQ.context(1);
        this.serverSocket = this.serverContext.socket(ZMQ.REP);
        this.serverSocket.bind("tcp://127.0.0.1:5555");

        this.serverThread = new Thread(() -> {
            byte[] request = this.serverSocket.recv(0);
            if (request != null) {
                this.serverSocket.send(responseText.getBytes(ZMQ.CHARSET), 0);
            }
        });
        this.serverThread.start();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        if (this.serverThread != null) {
            this.serverThread.join(1000);
        }
        if (this.serverSocket != null) {
            this.serverSocket.close();
        }
        if (this.serverContext != null) {
            this.serverContext.term();
        }
    }

    @Test
    void shouldReturnProjectsFromResponse() {
        JSONObject project1 = new JSONObject();
        project1.put("id", 1);
        project1.put("name", "Project One");

        JSONObject project2 = new JSONObject();
        project2.put("id", 2);
        project2.put("name", "Project Two");

        JSONArray projects = new JSONArray();
        projects.put(project1);
        projects.put(project2);

        JSONObject response = new JSONObject();
        response.put("projects", projects);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetProjectRequest request = new GetProjectRequest("token123");

        List<Project> result = server.requestUserProjects(request);

        assertEquals(2, result.size());

        server.close();
    }

    @Test
    void shouldReturnEmptyListWhenNoProjects() {
        JSONObject response = new JSONObject();
        response.put("projects", new JSONArray());

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetProjectRequest request = new GetProjectRequest("token123");

        List<Project> result = server.requestUserProjects(request);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        server.close();
    }
}
