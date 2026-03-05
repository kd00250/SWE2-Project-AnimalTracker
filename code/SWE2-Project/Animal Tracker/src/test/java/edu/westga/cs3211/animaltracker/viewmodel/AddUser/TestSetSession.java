package edu.westga.cs3211.animaltracker.viewmodel.AddUser;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.AddUserViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetSession {
    @Test
    void testSetSessionStoresSession() {
        AddUserViewModel vm = new AddUserViewModel();
        LoginResponse session = new LoginResponse("validToken", 100);
        LocalServer localServer = new LocalServer();
        vm.setSession(session, localServer);

        assertEquals(session, vm.getSession());
        assertEquals(localServer, vm.getServerService());
    }
}
