package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetAllScientistsRequests;
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

public class TestRequestAllScientistsFromServer {

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
    void shouldReturnScientistsFromResponse() {
        JSONObject user1 = new JSONObject();
        user1.put("username", "alice");
        user1.put("password", "pass1");

        JSONObject user2 = new JSONObject();
        user2.put("username", "bob");
        user2.put("password", "pass2");

        JSONArray users = new JSONArray();
        users.put(user1);
        users.put(user2);

        JSONObject response = new JSONObject();
        response.put("users", users);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetAllScientistsRequests request = new GetAllScientistsRequests("token123");

        List<User> result = server.requestAllScientistsFromServer(request);

        assertEquals(2, result.size());

        server.close();
    }

    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        JSONObject response = new JSONObject();
        response.put("users", new JSONArray());

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetAllScientistsRequests request = new GetAllScientistsRequests("token123");

        List<User> result = server.requestAllScientistsFromServer(request);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        server.close();
    }
}
