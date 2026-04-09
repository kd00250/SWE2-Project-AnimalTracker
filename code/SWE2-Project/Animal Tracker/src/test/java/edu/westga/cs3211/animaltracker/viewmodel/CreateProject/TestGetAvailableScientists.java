package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetAvailableScientists {
    private String token;
    @BeforeEach
    void setUp() {
        DataStorage.reset();
        User user = new User("Bill", "ppp", Role.ADMIN);
        DataStorage.getUsers().add(user);
        this.token = DataStorage.generateTokenForUser(user);
    }

    @Test
    void testGetAvailableScientist() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.setSession(new LoginResponse(token, 1000), new LocalServer());

        assertEquals(1, vm.getAvailableScientists().size());
        assertEquals("Bob", vm.getAvailableScientists().getFirst().getUsername());
        assertEquals("1234", vm.getAvailableScientists().getFirst().getPassword());
        assertEquals(Role.SCIENTIST, vm.getAvailableScientists().getFirst().getRole());
    }
}
