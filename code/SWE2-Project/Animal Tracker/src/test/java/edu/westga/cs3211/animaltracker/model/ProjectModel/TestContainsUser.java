package edu.westga.cs3211.animaltracker.model.ProjectModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.Role;

import java.util.ArrayList;

/**
 * Tests for the containsUser method in Project class.
 * @author mrocker1
 */
class TestContainsUser {

    @Test
    void testContainsUserWhenEmpty() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        User user = new User("testuser", "password", Role.SCIENTIST);

        assertFalse(project.containsUser(user), "checking user not in empty list");
    }

    @Test
    void testContainsUserWhenPresent() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        User user = new User("testuser", "password", Role.SCIENTIST);

        project.addUser(user);

        assertTrue(project.containsUser(user), "checking user is in list");
    }

    @Test
    void testContainsUserWhenNotPresent() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);

        project.addUser(user1);

        assertFalse(project.containsUser(user2), "checking user2 not in list");
    }

    @Test
    void testContainsUserWithMultipleUsers() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        User user3 = new User("user3", "password3", Role.GUEST);
        User user4 = new User("user4", "password4", Role.ADMIN);

        project.addUser(user1);
        project.addUser(user2);
        project.addUser(user3);

        assertTrue(project.containsUser(user1), "checking user1 is in list");
        assertTrue(project.containsUser(user2), "checking user2 is in list");
        assertTrue(project.containsUser(user3), "checking user3 is in list");
        assertFalse(project.containsUser(user4), "checking user4 not in list");
    }

    @Test
    void testContainsNullUser() {
        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());

        assertFalse(project.containsUser(null), "checking null user returns false");
    }
}