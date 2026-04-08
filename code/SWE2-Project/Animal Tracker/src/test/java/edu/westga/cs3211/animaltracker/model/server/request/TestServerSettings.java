package edu.westga.cs3211.animaltracker.model.server.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestServerSettings {

    @Test
    void shouldCreateServerSettingsObject() {
        SeverSettings settings = new SeverSettings();
        assertNotNull(settings);
    }
}
