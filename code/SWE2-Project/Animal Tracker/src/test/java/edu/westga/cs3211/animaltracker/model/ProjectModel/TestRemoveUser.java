package edu.westga.cs3211.animaltracker.model.ProjectModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.Role;

/**
 * Tests for the removeUser method in Project class.
 * @author mrocker1
 */
class TestRemoveUser {

    @Test
    void testRemoveNullUser() {
        Project project = new Project("Test Project");

        assertThrows(IllegalArgumentException.class, () -> project.removeUser(null));
    }

    @Test
    void testRemoveUserNotInList() {
        Project project = new Project("Test Project");
        User user = new User("testuser", "password", Role.SCIENTIST);

        assertThrows(IllegalArgumentException.class, () -> project.removeUser(user));
    }

    @Test
    void testRemoveValidUser() {
        Project project = new Project("Test Project");
        User user = new User("testuser", "password", Role.SCIENTIST);

        project.addUser(user);
        project.removeUser(user);

        assertEquals(0, project.getUsers().size(), "checking users list is empty");
        assertFalse(project.containsUser(user), "checking user is not in list");
    }

    @Test
    void testRemoveUserFromMultipleUsers() {
        Project project = new Project("Test Project");
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        User user3 = new User("user3", "password3", Role.GUEST);

        project.addUser(user1);
        project.addUser(user2);
        project.addUser(user3);

        project.removeUser(user2);

        assertEquals(2, project.getUsers().size(), "checking size is 2");
        assertTrue(project.containsUser(user1), "checking user1 still in list");
        assertFalse(project.containsUser(user2), "checking user2 removed");
        assertTrue(project.containsUser(user3), "checking user3 still in list");
    }

    @Test
    void testRemoveAllUsers() {
        Project project = new Project("Test Project");
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);

        project.addUser(user1);
        project.addUser(user2);

        project.removeUser(user1);
        project.removeUser(user2);

        assertEquals(0, project.getUsers().size(), "checking all users removed");
    }

    @Test
    void testRemoveSameUserTwice() {
        Project project = new Project("Test Project");
        User user = new User("testuser", "password", Role.SCIENTIST);

        project.addUser(user);
        project.removeUser(user);

        assertThrows(IllegalArgumentException.class, () -> project.removeUser(user), "checking removing same user twice throws exception");
    }
}