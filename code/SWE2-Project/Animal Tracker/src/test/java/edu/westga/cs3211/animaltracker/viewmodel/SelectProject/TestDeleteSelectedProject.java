package edu.westga.cs3211.animaltracker.viewmodel.SelectProject;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.SelectProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestDeleteSelectedProject {

    private User user;
    private String token;
    private LoginResponse loginResponse;
    private LocalServer localServer;

    @BeforeEach
    void setUp() {
        DataStorage.getUsers().clear();
        DataStorage.getProjects().clear();
        DataStorage.getUsernameMap().clear();
        this.localServer = new LocalServer();
        this.user = new User("test", "1234", Role.SCIENTIST);
        this.token = DataStorage.generateTokenForUser(this.user);
        this.loginResponse = new LoginResponse(this.token, 1000);
    }

    @Test
    void testDeleteSelectedProject() {
        DataStorage.getUsers().add(this.user);
        var users = new ArrayList<User>();
        users.add(this.user);
        var project = new Project("Test", new ArrayList<>(), new ArrayList<>(), users);
        DataStorage.addProject(project);
        var vm = new SelectProjectViewModel();
        vm.setSession(this.loginResponse, this.localServer);
        vm.loadProjects();
        vm.selectedProjectProperty().setValue(vm.projectsProperty().getFirst());

        var result = vm.deleteSelectedProject();

        assertTrue(result);
    }

    @Test
    void testDeleteProjectNoneSelected() {
        var vm = new SelectProjectViewModel();
        var result = vm.deleteSelectedProject();

        assertFalse(result);
    }
}