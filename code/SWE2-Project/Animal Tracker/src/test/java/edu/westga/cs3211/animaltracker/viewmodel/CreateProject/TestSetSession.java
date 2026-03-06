package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetSession {
    @Test
    void testSetSessionStoresSession() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        LoginResponse session = new LoginResponse("validToken", 100);
        LocalServer localServer = new LocalServer();
        vm.setSession(session, localServer);

        assertEquals(session, vm.getSession());
        assertEquals(localServer, vm.getServerService());
    }
}
