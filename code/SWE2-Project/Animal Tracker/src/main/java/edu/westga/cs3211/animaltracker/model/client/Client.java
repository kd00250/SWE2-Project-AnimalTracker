package edu.westga.cs3211.animaltracker.model.client;

import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import org.zeromq.ZMQ;
import org.json.JSONObject;

/**
 * the connection point the server from the client.
 */
public class Client {

    private static final String SERVER_ADDRESS = "tcp://127.0.0.1:5555";

    private final Context context;
    private final Socket socket;

    /**
     * Makes a new client (which initially connects to the python server).
     */
    public Client() {
        this.context = ZMQ.context(1);
        this.socket = this.context.socket(ZMQ.REQ);
        this.socket.connect(SERVER_ADDRESS);
    }

    /**
     * Sends the json string.
     * @param request the request to be sent to the server
     * @return the response from the server
     */
    public JSONObject send(Request request) {
        request.validateRequest();

        JSONObject jsonRequest = request.toJson();

        try {
            this.socket.send(jsonRequest.toString().getBytes(ZMQ.CHARSET), 0);

            byte[] reply = this.socket.recv(0);
            String rawResponse = new String(reply, ZMQ.CHARSET);

            return new JSONObject(rawResponse);
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("success", false);
            error.put("message", "Communication error: " + e.getMessage());
            return error;
        }
    }

    /**
     * closes the connection to the python server.
     */
    public void close() {
        this.socket.close();
        this.context.term();
    }
}
