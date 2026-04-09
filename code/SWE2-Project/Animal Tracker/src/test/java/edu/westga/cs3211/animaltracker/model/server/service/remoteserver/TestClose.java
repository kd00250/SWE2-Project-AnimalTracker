package edu.westga.cs3211.animaltracker.model.server.service.remoteserver;

import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestClose {

    @Test
    void shouldNotThrowWhenCalled() {
        RemoteServer server = new RemoteServer();

        assertDoesNotThrow(server::close);
    }
}
