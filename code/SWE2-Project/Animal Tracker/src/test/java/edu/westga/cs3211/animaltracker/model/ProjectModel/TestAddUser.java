package edu.westga.cs3211.animaltracker.model.ProjectModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.Role;

/**
 * Tests for the addUser method in Project class.
 * @author mrocker1
 */
class TestAddUser {

    @Test
    void testAddNullUser() {
        Project project = new Project("Test Project");

        assertThrows(IllegalArgumentException.class, () -> project.addUser(null));
    }

    @Test
    void testAddValidUser() {
        Project project = new Project("Test Project");
        User user = new User("testuser", "password123", Role.SCIENTIST);

        project.addUser(user);

        assertEquals(1, project.getUsers().size(), "checking users list size");
        assertTrue(project.getUsers().contains(user), "checking user is in list");
    }

    @Test
    void testAddMultipleUsers() {
        Project project = new Project("Test Project");
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        User user3 = new User("user3", "password3", Role.GUEST);

        project.addUser(user1);
        project.addUser(user2);
        project.addUser(user3);

        assertEquals(3, project.getUsers().size(), "checking users list size");
        assertTrue(project.getUsers().contains(user1), "checking user1 is in list");
        assertTrue(project.getUsers().contains(user2), "checking user2 is in list");
        assertTrue(project.getUsers().contains(user3), "checking user3 is in list");
    }

    @Test
    void testAddDuplicateUser() {
        Project project = new Project("Test Project");
        User user = new User("duplicate", "password", Role.SCIENTIST);

        project.addUser(user);

        assertThrows(IllegalArgumentException.class, () -> project.addUser(user));
    }

    @Test
    void testAddUserToProjectWithExistingUsers() {
        Project project = new Project("Test Project");
        User user1 = new User("existing", "pass1", Role.SCIENTIST);
        User user2 = new User("newuser", "pass2", Role.ADMIN);

        project.addUser(user1);
        int initialSize = project.getUsers().size();

        project.addUser(user2);

        assertEquals(initialSize + 1, project.getUsers().size(), "checking size increased by 1");
        assertTrue(project.containsUser(user2), "checking new user exists");
    }
}