package edu.westga.cs3211.animaltracker.model.ProjectModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.Role;

/**
 * Tests for the getUsers method in Project class.
 * @author mrocker1
 */
class TestGetUsers {

    @Test
    void testGetUsersWhenEmpty() {
        Project project = new Project("Test Project");

        assertNotNull(project.getUsers(), "checking users list is not null");
        assertEquals(0, project.getUsers().size(), "checking users list is empty");
    }

    @Test
    void testGetUsersWithOneUser() {
        Project project = new Project("Test Project");
        User user = new User("testuser", "password", Role.SCIENTIST);

        project.addUser(user);

        assertEquals(1, project.getUsers().size(), "checking users list size");
        assertTrue(project.getUsers().contains(user), "checking user is in list");
    }

    @Test
    void testGetUsersWithMultipleUsers() {
        Project project = new Project("Test Project");
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        User user3 = new User("user3", "password3", Role.GUEST);

        project.addUser(user1);
        project.addUser(user2);
        project.addUser(user3);

        assertEquals(3, project.getUsers().size(), "checking users list size");
    }

    @Test
    void testGetUsersReturnsCorrectList() {
        Project project = new Project("Test Project");
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.ADMIN);

        project.addUser(user1);
        project.addUser(user2);

        var users = project.getUsers();

        assertTrue(users.contains(user1), "checking list contains user1");
        assertTrue(users.contains(user2), "checking list contains user2");
    }
}