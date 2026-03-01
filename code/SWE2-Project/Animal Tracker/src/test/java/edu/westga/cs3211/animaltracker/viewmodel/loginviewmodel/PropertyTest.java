package edu.westga.cs3211.animaltracker.viewmodel.loginviewmodel;

import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyTest {
    @Test
    void testPropertiesAreInitialized() {
        var vm = new LoginViewModel();
        vm.usernameProperty().set("Bob");
        vm.passwordProperty().set("1234");
        vm.processLoginRequest();
        assertNotNull(vm.getLoginResponse());
        assertNotNull(vm.getServerService());
    }
}
