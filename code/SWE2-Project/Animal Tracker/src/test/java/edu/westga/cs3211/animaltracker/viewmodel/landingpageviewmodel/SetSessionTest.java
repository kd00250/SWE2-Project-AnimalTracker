package edu.westga.cs3211.animaltracker.viewmodel.landingpageviewmodel;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.LandingPageViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SetSessionTest {
    @Test
    void testValidSessionSet() {
        var session = new LoginResponse("token", 1000);
        var server = new LocalServer();
        var viewmodel = new LandingPageViewModel();
        viewmodel.setSession(session, server);
        assertEquals(session, viewmodel.getSession());
        assertEquals(server, viewmodel.getServerService());
    }

    @Test
    void testInvalidSessionSet() {
        var server = new LocalServer();
        var viewmodel = new LandingPageViewModel();
        assertThrows(IllegalArgumentException.class, () -> {
            viewmodel.setSession(null, server);
        });
    }

    @Test
    void testInvalidServerSet() {
        var session = new LoginResponse("token", 1000);
        var viewmodel = new LandingPageViewModel();
        assertThrows(IllegalArgumentException.class, () -> {
            viewmodel.setSession(session, null);
        });
    }
}
