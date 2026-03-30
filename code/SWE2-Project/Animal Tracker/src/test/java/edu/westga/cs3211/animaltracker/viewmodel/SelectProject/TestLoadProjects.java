package edu.westga.cs3211.animaltracker.viewmodel.SelectProject;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.SelectProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestLoadProjects {

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
    void TestLoadNoProjects() {
        var vm = new SelectProjectViewModel();
        vm.setSession(this.loginResponse, this.localServer);

        vm.loadProjects();

        assertTrue(vm.projectsProperty().get().isEmpty());
        assertEquals(vm.getSession(), this.loginResponse);
        assertEquals(vm.getServerService(), this.localServer);
    }

    @Test
    void TestLoadUserProjects() {
        DataStorage.getUsers().add(this.user);
        var users = new ArrayList<User>();
        users.add(this.user);
        Project project = new Project(users,  "Test", new ArrayList<>());
        DataStorage.addProject(project);
        var vm = new SelectProjectViewModel();
        vm.setSession(this.loginResponse, this.localServer);

        vm.loadProjects();

        assertTrue(vm.projectsProperty().get().contains(project));
    }

    @Test
    void TestLoadProjectsAfterAdding() {
        DataStorage.getUsers().add(this.user);
        var users = new ArrayList<User>();
        users.add(this.user);
        Project project1 = new Project(users,  "Test", new ArrayList<>());
        DataStorage.addProject(project1);
        var vm = new SelectProjectViewModel();
        vm.setSession(this.loginResponse, this.localServer);
        vm.loadProjects();
        Project project2 = new Project(users,  "Test", new ArrayList<>());
        DataStorage.addProject(project2);
        vm.refreshProjects();
        assertTrue(vm.projectsProperty().get().contains(project2));
    }
}