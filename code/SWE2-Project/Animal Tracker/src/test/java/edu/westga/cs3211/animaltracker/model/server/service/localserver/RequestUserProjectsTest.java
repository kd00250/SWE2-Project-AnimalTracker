package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestUserProjectsTest {

    @Test
    void testNullRequest() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.requestUserProjects(null);
        });
    }

    @Test
    void testInvalidToken() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.requestUserProjects(new UserDataRequest("-1"));
        });
    }


}
