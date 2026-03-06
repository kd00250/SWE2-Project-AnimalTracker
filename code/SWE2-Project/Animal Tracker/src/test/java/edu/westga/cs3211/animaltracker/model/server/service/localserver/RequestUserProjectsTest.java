package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RequestUserProjectsTest {

    @Test
    void testNullRequest() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.requestUserProjects(null);
        });
    }

    @Test
    void testInvalidToken() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.requestUserProjects(new UserDataRequest("-1"));
        });
    }

    @Test
    void testGetProjectsThatContainUserMultiple() {
        var server = new LocalServer();
        DataStorage.getAnimals().clear();
        DataStorage.getUsers().clear();
        DataStorage.getProjects().clear();
        DataStorage.getTokenMap().clear();
        DataStorage.getExpirationMap().clear();
        var user1 = new User("Tim", "bob", Role.SCIENTIST);
        var user2 = new User("Sam", "1234", Role.SCIENTIST);
        var user3 = new User("Dan", "1234", Role.SCIENTIST);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        var firstToken = DataStorage.generateTokenForUser(user1);

        var project1 = new Project(users, "project1", new ArrayList<Animal>());
        var project2 = new Project(users, "project2", new ArrayList<>());
        DataStorage.addProject(project1);
        DataStorage.addProject(project2);
        var projects = server.requestUserProjects(new UserDataRequest(firstToken));
        for (var project : projects) {
            assertTrue(project.getUsers().contains(user1));
        }
    }

    @Test
    void testGetProjectHasOneMatchingProject() {
        var server = new LocalServer();
        DataStorage.getAnimals().clear();
        DataStorage.getUsers().clear();
        DataStorage.getProjects().clear();
        DataStorage.getTokenMap().clear();
        DataStorage.getExpirationMap().clear();
        var user1 = new User("Tim", "bob", Role.SCIENTIST);
        var user2 = new User("Sam", "1234", Role.SCIENTIST);
        var user3 = new User("Dan", "1234", Role.SCIENTIST);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        var firstToken = DataStorage.generateTokenForUser(user1);

        var project1 = new Project(users, "project1", new ArrayList<Animal>());
        var project2 = new Project(new ArrayList<User>(), "project2", new ArrayList<>());
        DataStorage.addProject(project1);
        DataStorage.addProject(project2);
        var projects = server.requestUserProjects(new UserDataRequest(firstToken));
        for (var project : projects) {
            assertTrue(project.getUsers().contains(user1));
        }
    }

    @Test
    void testGetProjectNoMatch() {
        var server = new LocalServer();
        DataStorage.getAnimals().clear();
        DataStorage.getUsers().clear();
        DataStorage.getProjects().clear();
        DataStorage.getTokenMap().clear();
        DataStorage.getExpirationMap().clear();
        var user1 = new User("Tim", "bob", Role.SCIENTIST);
        var user2 = new User("Sam", "1234", Role.SCIENTIST);
        var user3 = new User("Dan", "1234", Role.SCIENTIST);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        var firstToken = DataStorage.generateTokenForUser(user1);

        var project1 = new Project(new ArrayList<User>(), "project1", new ArrayList<Animal>());
        var project2 = new Project(new ArrayList<User>(), "project2", new ArrayList<>());
        DataStorage.addProject(project1);
        DataStorage.addProject(project2);
        var projects = server.requestUserProjects(new UserDataRequest(firstToken));
        assertTrue(projects.isEmpty());
    }



}
