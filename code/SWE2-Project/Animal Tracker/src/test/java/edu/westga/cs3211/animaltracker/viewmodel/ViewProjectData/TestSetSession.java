package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetSession {
    @Test
    void testSetSessionStoresSession() {
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        LoginResponse session = new LoginResponse("validToken", 100);
        LocalServer localServer = new LocalServer();
        vm.setSession(session, localServer);

        assertEquals(session, vm.getSession());
        assertEquals(localServer, vm.getServerService());
    }
}
