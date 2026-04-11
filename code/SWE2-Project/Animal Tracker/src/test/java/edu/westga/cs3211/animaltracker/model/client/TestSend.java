package edu.westga.cs3211.animaltracker.model.client;

import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSend {
    private Context serverContext;
    private Socket serverSocket;

    private static class FakeRequest extends Request {
        private final JSONObject json;

        FakeRequest(JSONObject json) {
            this.json = json;
        }

        @Override
        public void validateRequest() {
        }

        @Override
        public JSONObject toJson() {
            return this.json;
        }
    }

    @AfterEach
    void tearDown() {
        if (this.serverSocket != null) {
            this.serverSocket.close();
        }
        if (this.serverContext != null) {
            this.serverContext.term();
        }
    }

    @Test
    void shouldReturnParsedJsonResponseWhenCommunicationSucceeds() throws InterruptedException {
        this.serverContext = ZMQ.context(1);
        this.serverSocket = this.serverContext.socket(ZMQ.REP);
        this.serverSocket.bind("tcp://127.0.0.1:5555");

        Thread serverThread = new Thread(() -> {
            byte[] message = this.serverSocket.recv(0);
            if (message != null) {
                JSONObject response = new JSONObject();
                response.put("success", true);
                response.put("message", "ok");
                this.serverSocket.send(response.toString().getBytes(ZMQ.CHARSET), 0);
            }
        });
        serverThread.start();

        Context clientContext = ZMQ.context(1);
        Client client = new Client(clientContext);

        JSONObject requestJson = new JSONObject();
        requestJson.put("action", "test");
        FakeRequest request = new FakeRequest(requestJson);

        JSONObject result = client.send(request);

        assertTrue(result.getBoolean("success"));
        assertEquals("ok", result.getString("message"));

        client.close();
        serverThread.join();
    }

    @Test
    void shouldReturnErrorJsonWhenResponseIsNotValidJson() throws InterruptedException {
        this.serverContext = ZMQ.context(1);
        this.serverSocket = this.serverContext.socket(ZMQ.REP);
        this.serverSocket.bind("tcp://127.0.0.1:5555");

        Thread serverThread = new Thread(() -> {
            byte[] message = this.serverSocket.recv(0);
            if (message != null) {
                this.serverSocket.send("not valid json".getBytes(ZMQ.CHARSET), 0);
            }
        });
        serverThread.start();

        Context clientContext = ZMQ.context(1);
        Client client = new Client(clientContext);

        JSONObject requestJson = new JSONObject();
        requestJson.put("action", "test");
        FakeRequest request = new FakeRequest(requestJson);

        JSONObject result = client.send(request);

        assertFalse(result.getBoolean("success"));
        assertTrue(result.getString("message").contains("Communication error"));

        client.close();
        serverThread.join();
    }
}
