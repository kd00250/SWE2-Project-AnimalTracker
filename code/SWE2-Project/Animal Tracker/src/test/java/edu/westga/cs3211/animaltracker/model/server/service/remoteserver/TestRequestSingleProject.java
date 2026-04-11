package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetSingleProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class TestRequestSingleProject {

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
    void shouldReturnProjectWithValidAnimal() {
        String animalClass = AnimalClass.values()[0].name();

        JSONObject animalJson = new JSONObject();
        animalJson.put("Class", animalClass);
        animalJson.put("Height", 10.0);
        animalJson.put("Weight", 20.0);
        animalJson.put("Length", 30.0);
        animalJson.put("TagID", 1);
        animalJson.put("Description", "Valid animal");

        JSONArray animals = new JSONArray();
        animals.put(animalJson);

        JSONObject response = new JSONObject();
        response.put("animals", animals);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSingleProjectRequest request =
                new GetSingleProjectRequest("token123", "Project A", 5);

        Project result = server.requestSingleProject(request);

        assertNotNull(result);
        assertEquals("Project A", result.getName());
        assertEquals(5, result.getId());
        assertEquals(1, result.getAnimals().size());

        server.close();
    }

    @Test
    void shouldSkipInvalidAnimal() {
        JSONObject invalidAnimal = new JSONObject();
        invalidAnimal.put("Class", "");
        invalidAnimal.put("Height", 10.0);
        invalidAnimal.put("Weight", 20.0);
        invalidAnimal.put("Length", 30.0);
        invalidAnimal.put("TagID", 1);
        invalidAnimal.put("Description", "Invalid animal");

        JSONArray animals = new JSONArray();
        animals.put(invalidAnimal);

        JSONObject response = new JSONObject();
        response.put("animals", animals);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSingleProjectRequest request =
                new GetSingleProjectRequest("token123", "Project A", 5);

        Project result = server.requestSingleProject(request);

        assertNotNull(result);
        assertTrue(result.getAnimals().isEmpty());

        server.close();
    }
}
