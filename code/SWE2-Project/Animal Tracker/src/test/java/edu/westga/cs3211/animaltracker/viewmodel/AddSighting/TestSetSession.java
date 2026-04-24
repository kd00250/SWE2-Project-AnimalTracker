package edu.westga.cs3211.animaltracker.viewmodel.AddSighting;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.AddSightingViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSetSession {
    @Test
    void testSetSessionStoresSession() {
        AddSightingViewModel vm = new AddSightingViewModel();
        LoginResponse session = new LoginResponse("validToken", 100);
        LocalServer localServer = new LocalServer();
        vm.setSession(session, localServer);

        assertEquals(session, vm.getSession());
        assertEquals(localServer, vm.getServerService());
    }
}