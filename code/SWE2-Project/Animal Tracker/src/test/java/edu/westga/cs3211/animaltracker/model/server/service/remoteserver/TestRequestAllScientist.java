package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestRequestAllScientist {

    @Test
    void shouldReturnEmptyList() {
        RemoteServer server = new RemoteServer();
        UserDataRequest request = new UserDataRequest("token123");

        List<User> result = server.requestAllScientist(request);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        server.close();
    }
}
