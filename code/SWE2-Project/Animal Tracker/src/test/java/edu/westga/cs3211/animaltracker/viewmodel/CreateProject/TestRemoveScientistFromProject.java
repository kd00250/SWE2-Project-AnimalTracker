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

class TestRemoveScientistFromProject {
    private String token;
    @BeforeEach
    void setUp() {
        DataStorage.reset();
        var user0 = new User("Bob", "12341234", Role.SCIENTIST);
        DataStorage.getUsers().add(user0);
        this.token = DataStorage.generateTokenForUser(user0);
    }

    @Test
    void testRemoveScientistFromProject() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.addScientistToProject(vm.getAvailableScientists().getFirst());
        vm.removeScientistFromProject(vm.getAvailableScientists().getFirst());

        assertEquals(0, vm.getAddedScientist().size());
    }
}
