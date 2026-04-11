package edu.westga.cs3211.animaltracker.viewmodel.loginviewmodel;

import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SetServerTest {

    @Test
    void testSetValidServer() {
        var vm = new LoginViewModel();
        var local =  new LocalServer();
        vm.setServer(local);
        assertEquals(local, vm.getServerService());
    }

    @Test
    void testSetInvalidServer() {
        var vm = new LoginViewModel();
        assertThrows(IllegalArgumentException.class, () -> {
            vm.setServer(null);
        });
    }
}
