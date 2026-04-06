package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCreateProject {
    private String token;
    @BeforeEach
    void setUp() {
        DataStorage.reset();
        var user0 = new User("Bob", "123456", Role.SCIENTIST);
        DataStorage.getUsers().add(user0);
        this.token = DataStorage.generateTokenForUser(user0);
    }

    @Test
    void testCreateProjectNoScientists() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.getProjectNameProperty().setValue("Baboons");
        vm.getProjectLocationProperty().setValue("");
        vm.createProject(vm.getProjectNameProperty().get(), vm.getAddedScientist());

        assertEquals("Baboons", DataStorage.getProjects().get(2).getName());
        assertEquals(0, DataStorage.getProjects().get(2).getUsers().size());
    }

    @Test
    void testCreateProjectWithScientists() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());

        var user = new User("John Doe", "123456789", Role.SCIENTIST);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        vm.createProject("Pokemon", users);
        assertEquals(1, DataStorage.getProjects().get(2).getUsers().size());
        assertEquals(user, DataStorage.getProjects().get(2).getUsers().getFirst());
    }
}
