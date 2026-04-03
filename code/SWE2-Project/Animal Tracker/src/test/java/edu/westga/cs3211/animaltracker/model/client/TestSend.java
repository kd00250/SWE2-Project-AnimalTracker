//package edu.westga.cs3211.animaltracker.model.client;
//
//import edu.westga.cs3211.animaltracker.model.server.request.Request;
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.zeromq.ZMQ;
//import org.zeromq.ZMQ.Context;
//import org.zeromq.ZMQ.Socket;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class TestSend {
//
//    private Context mockContext;
//    private Socket mockSocket;
//    private Request mockRequest;
//    private Client client;
//
//    @BeforeEach
//    void setUp() {
//        this.mockContext = mock(Context.class);
//        this.mockSocket = mock(Socket.class);
//        this.mockRequest = mock(Request.class);
//
//        when(this.mockContext.socket(ZMQ.REQ)).thenReturn(this.mockSocket);
//
//        JSONObject fakePayload = new JSONObject();
//        fakePayload.put("type", "GET_ANIMALS");
//        when(this.mockRequest.toJson()).thenReturn(fakePayload);
//        doNothing().when(this.mockRequest).validateRequest();
//
//        this.client = new Client(this.mockContext);
//    }
//    @Test
//    void send_socketThrowsException_returnsErrorResponse() {
//        when(this.mockSocket.recv(0)).thenThrow(new RuntimeException("network failure"));
//
//        JSONObject result = this.client.send(this.mockRequest);
//
//        assertFalse(result.getBoolean("success"));
//        assertTrue(result.getString("message").contains("Communication error"));
//    }
//}
