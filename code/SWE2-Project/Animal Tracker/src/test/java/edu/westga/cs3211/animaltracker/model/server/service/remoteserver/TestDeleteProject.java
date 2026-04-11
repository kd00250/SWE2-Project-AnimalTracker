package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.server.request.data.DeleteProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestDeleteProject {

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
    void shouldNotThrowWhenCalled() {
        JSONObject response = new JSONObject();
        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        DeleteProjectRequest request = new DeleteProjectRequest("token123", 5);

        assertDoesNotThrow(() -> server.deleteProject(request));

        server.close();
    }
}
