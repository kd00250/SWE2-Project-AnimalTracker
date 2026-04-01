package edu.westga.cs3211.animaltracker.model.client;

import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import org.zeromq.ZMQ;
import org.json.JSONObject;

/**
 * the connection point the server from the client.
 */
public class Client {

    public static void run() {
        Context context = ZMQ.context(1);

        System.out.println("Connecting to Python server...");
        Socket socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://127.0.0.1:5555");

        JSONObject request = new JSONObject();
        request.put("action", "greet");
        request.put("message", "Hello");

        String jsonStr = request.toString();
        System.out.println("Client - Sending: " + jsonStr);
        socket.send(jsonStr.getBytes(ZMQ.CHARSET), 0);

        byte[] reply = socket.recv(0);
        String rawResponse = new String(reply, ZMQ.CHARSET);
        //deserialize
        JSONObject response = new JSONObject(rawResponse);
        System.out.println("Client - Received: " + response.toString());

        System.out.println("Client - Sending exit");
        socket.send("exit".getBytes(ZMQ.CHARSET), 0);

        socket.close();
        context.term();
    }
    //todo workout send method
    public void send(JSONObject json) {
        return;
    }
}
