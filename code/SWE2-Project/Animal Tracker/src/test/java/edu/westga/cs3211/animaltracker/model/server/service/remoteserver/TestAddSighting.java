package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestAddSighting {

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
    void shouldReturnTrueWhenStatusIsSuccess() {
        JSONObject response = new JSONObject();
        response.put("status", "success");

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();

        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal,
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees"
        );

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        boolean result = server.addSighting(request);

        assertTrue(result);

        server.close();
    }

    @Test
    void shouldReturnFalseWhenStatusIsError() {
        JSONObject response = new JSONObject();
        response.put("status", "error");

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();

        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        Sighting sighting = new Sighting(
                animal,
                "Forest Area A",
                33.7490,
                -84.3880,
                LocalDateTime.of(2026, 4, 10, 14, 30),
                "Seen near the trees"
        );

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        boolean result = server.addSighting(request);

        assertFalse(result);

        server.close();
    }
}
