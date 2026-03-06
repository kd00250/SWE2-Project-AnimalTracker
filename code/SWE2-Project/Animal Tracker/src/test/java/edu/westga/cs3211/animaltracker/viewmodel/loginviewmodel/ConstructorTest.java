package edu.westga.cs3211.animaltracker.viewmodel.loginviewmodel;

import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest {

    @Test
    void validConstructor() {
        var viewmodel = new LoginViewModel();
        var usernameProperty =  viewmodel.usernameProperty();
        var passwordProperty = viewmodel.passwordProperty();
        assertEquals("",  usernameProperty.get());
        assertEquals("",  passwordProperty.get());
    }
}
