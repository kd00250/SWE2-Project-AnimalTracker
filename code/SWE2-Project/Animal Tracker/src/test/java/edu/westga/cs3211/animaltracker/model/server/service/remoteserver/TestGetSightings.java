package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetSightingRequest;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestGetSightings {
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
    void testBuildSightingsFromResponseWithNullResponseThrowsException() throws Exception {
        RemoteServer server = new RemoteServer();

        var method = RemoteServer.class.getDeclaredMethod("buildSightingsFromResponse", JSONObject.class);
        method.setAccessible(true);

        Exception exception = assertThrows(Exception.class, () -> method.invoke(server, new Object[]{null}));

        assertNotNull(exception.getCause());
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
        assertEquals("Response cannot be null", exception.getCause().getMessage());

        server.close();
    }

    @Test
    void testGetSightingsReturnsOneSightingFromServer() {
        JSONObject response = getJsonObject();

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5, result.getFirst().getAnimalTagID());
        assertEquals("Forest", result.getFirst().getLocation());
        assertEquals(33.12345, result.getFirst().getLatitude());
        assertEquals(-84.54321, result.getFirst().getLongitude());
        assertEquals(LocalDateTime.parse("2026-04-19T10:30:00"), result.getFirst().getTime());
        assertEquals("Near water", result.getFirst().getNotes());

        server.close();
    }

    private static JSONObject getJsonObject() {
        JSONObject sightingJson = new JSONObject();
        sightingJson.put("animalTagID", 5);
        sightingJson.put("location", "Forest");
        sightingJson.put("latitude", 33.12345);
        sightingJson.put("longitude", -84.54321);
        sightingJson.put("time", "2026-04-19T10:30:00");
        sightingJson.put("notes", "Near water");

        JSONArray sightingsArray = new JSONArray();
        sightingsArray.put(sightingJson);

        JSONObject response = new JSONObject();
        response.put("sightings", sightingsArray);
        return response;
    }

    @Test
    void testGetSightingsReturnsEmptyListWhenServerReturnsNoSightings() {
        JSONObject response = new JSONObject();
        response.put("sightings", new JSONArray());

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        server.close();
    }

    @Test
    void testGetSightingsSkipsInvalidSightingAndReturnsValidOne() {
        JSONObject validSighting = new JSONObject();
        validSighting.put("animalTagID", 5);
        validSighting.put("location", "Lake");
        validSighting.put("latitude", 32.5);
        validSighting.put("longitude", -83.7);
        validSighting.put("time", "2026-04-19T08:00:00");
        validSighting.put("notes", "Morning sighting");

        JSONObject invalidSighting = new JSONObject();
        invalidSighting.put("animalTagID", 5);
        invalidSighting.put("location", "");
        invalidSighting.put("latitude", 32.5);
        invalidSighting.put("longitude", -83.7);
        invalidSighting.put("time", "2026-04-19T08:00:00");
        invalidSighting.put("notes", "Bad data");

        JSONArray sightingsArray = new JSONArray();
        sightingsArray.put(validSighting);
        sightingsArray.put(invalidSighting);

        JSONObject response = new JSONObject();
        response.put("sightings", sightingsArray);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Lake", result.getFirst().getLocation());

        server.close();
    }

    @Test
    void testGetSightingsReturnsSightingWithNullTimeWhenTimeIsMissing() {
        JSONObject sightingJson = new JSONObject();
        sightingJson.put("animalTagID", 5);
        sightingJson.put("location", "Forest");
        sightingJson.put("latitude", 33.12345);
        sightingJson.put("longitude", -84.54321);
        sightingJson.put("notes", "Near water");

        JSONArray sightingsArray = new JSONArray();
        sightingsArray.put(sightingJson);

        JSONObject response = new JSONObject();
        response.put("sightings", sightingsArray);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.getFirst().getTime());
        assertEquals("Near water", result.getFirst().getNotes());

        server.close();
    }

    @Test
    void testGetSightingsReturnsSightingWithNullNotesWhenNotesIsMissing() {
        JSONObject sightingJson = new JSONObject();
        sightingJson.put("animalTagID", 5);
        sightingJson.put("location", "Forest");
        sightingJson.put("latitude", 33.12345);
        sightingJson.put("longitude", -84.54321);
        sightingJson.put("time", "2026-04-19T10:30:00");

        JSONArray sightingsArray = new JSONArray();
        sightingsArray.put(sightingJson);

        JSONObject response = new JSONObject();
        response.put("sightings", sightingsArray);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(LocalDateTime.parse("2026-04-19T10:30:00"), result.getFirst().getTime());
        assertNull(result.getFirst().getNotes());

        server.close();
    }

    @Test
    void testGetSightingsReturnsSightingWithNullTimeWhenTimeIsJsonNull() {
        JSONObject sightingJson = new JSONObject();
        sightingJson.put("animalTagID", 5);
        sightingJson.put("location", "Forest");
        sightingJson.put("latitude", 33.12345);
        sightingJson.put("longitude", -84.54321);
        sightingJson.put("time", JSONObject.NULL);
        sightingJson.put("notes", "Near water");

        JSONArray sightingsArray = new JSONArray();
        sightingsArray.put(sightingJson);

        JSONObject response = new JSONObject();
        response.put("sightings", sightingsArray);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.getFirst().getTime());
        assertEquals("Near water", result.getFirst().getNotes());

        server.close();
    }

    @Test
    void testGetSightingsReturnsSightingWithNullNotesWhenNotesIsJsonNull() {
        JSONObject sightingJson = new JSONObject();
        sightingJson.put("animalTagID", 5);
        sightingJson.put("location", "Forest");
        sightingJson.put("latitude", 33.12345);
        sightingJson.put("longitude", -84.54321);
        sightingJson.put("time", "2026-04-19T10:30:00");
        sightingJson.put("notes", JSONObject.NULL);

        JSONArray sightingsArray = new JSONArray();
        sightingsArray.put(sightingJson);

        JSONObject response = new JSONObject();
        response.put("sightings", sightingsArray);

        this.startServerWithResponse(response.toString());

        RemoteServer server = new RemoteServer();
        GetSightingRequest request = new GetSightingRequest("token123", 5);

        List<Sighting> result = server.getSightings(request);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(LocalDateTime.parse("2026-04-19T10:30:00"), result.getFirst().getTime());
        assertNull(result.getFirst().getNotes());

        server.close();
    }
}
