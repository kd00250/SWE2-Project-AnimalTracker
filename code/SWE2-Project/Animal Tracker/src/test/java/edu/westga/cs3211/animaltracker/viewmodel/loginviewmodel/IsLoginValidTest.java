package edu.westga.cs3211.animaltracker.viewmodel.loginviewmodel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsLoginValidTest {
    private User user;
    @BeforeEach
    public void setup() {
        DataStorage.getUsers().clear();
        DataStorage.getTokenMap().clear();
        DataStorage.getUsernameMap().clear();
        this.user = new User("Bob", "12344", Role.SCIENTIST);
        DataStorage.generateTokenForUser(user);
    }
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
        vm.usernameProperty().set(this.user.username());
        vm.passwordProperty().set(this.user.password());
        vm.processLoginRequest();
        assertTrue(vm.isLoginValid());
    }
}
