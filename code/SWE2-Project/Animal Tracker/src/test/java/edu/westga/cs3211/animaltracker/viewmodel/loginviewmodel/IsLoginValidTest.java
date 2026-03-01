package edu.westga.cs3211.animaltracker.viewmodel.loginviewmodel;

import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsLoginValidTest {

    @Test
    void testLoginResponseNull() {
        var vm = new LoginViewModel();
        vm.usernameProperty().set(" ");
        vm.passwordProperty().set(" ");
        vm.processLoginRequest();
        assertFalse(vm.isLoginValid());
    }

    @Test
    void testLoginResponseValid() {
        var vm = new LoginViewModel();
        vm.usernameProperty().set("Bob");
        vm.passwordProperty().set("1234");
        vm.processLoginRequest();
        assertTrue(vm.isLoginValid());
    }
}
