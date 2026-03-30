package edu.westga.cs3211.animaltracker.viewmodel.AddUser;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.viewmodel.AddUserViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestCreateUser {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testCreateUser() {
        AddUserViewModel vm = new AddUserViewModel();
        vm.getUsername().setValue("Kaz");
        vm.getPassword().setValue("1234");
        vm.getRole().setValue(Role.SCIENTIST);

        vm.createNewUser();
        User user = DataStorage.getUsers().get(2);
        assertEquals("Kaz", user.username());
        assertEquals("1234", user.password());
        assertEquals(Role.SCIENTIST, user.role());
    }

    @Test
    void testInvalidCreateUser() {
        User newUser = new User("Kaz", "1234", Role.SCIENTIST);
        DataStorage.getUsers().add(newUser);
        AddUserViewModel vm = new AddUserViewModel();
        vm.getUsername().setValue("Kaz");
        vm.getPassword().setValue("1234");
        vm.getRole().setValue(Role.SCIENTIST);

        assertThrows(IllegalArgumentException.class, vm::createNewUser);
    }
}
