package edu.westga.cs3211.animaltracker.viewmodel.loginviewmodel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyTest {
    @Test
    void testPropertiesAreInitialized() {
        var user = new User("1234", "1234", Role.ADMIN);
        DataStorage.generateTokenForUser(user);
        var vm = new LoginViewModel();
        vm.setServer(new LocalServer());
        vm.usernameProperty().set(user.getUsername());
        vm.passwordProperty().set(user.getPassword());
        vm.processLoginRequest();
        assertNotNull(vm.getLoginResponse());
        assertNotNull(vm.getServerService());
    }
}
