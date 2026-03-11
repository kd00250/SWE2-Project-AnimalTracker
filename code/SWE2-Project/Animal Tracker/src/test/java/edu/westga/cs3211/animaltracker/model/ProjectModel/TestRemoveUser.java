package edu.westga.cs3211.animaltracker.model.ProjectModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.Role;

import java.util.ArrayList;

/**
 * Tests for the removeUser method in Project class.
 * @author mrocker1
 */
class TestRemoveUser {
    private Project project;
    @BeforeEach
    public void setup() {
        this.project = new Project(new ArrayList<>(), "Test Project", new ArrayList<>());
    }
    @Test
    void testRemoveNullUser() {

        assertThrows(IllegalArgumentException.class, () -> this.project.removeUser(null));
    }

    @Test
    void testRemoveUserNotInList() {
        User user = new User("testuser", "password", Role.SCIENTIST);
        project.getUsers().clear();
        assertThrows(IllegalArgumentException.class, () -> project.removeUser(user));
    }

    @Test
    void testRemoveValidUser() {
        User user = new User("testuser", "password", Role.SCIENTIST);
        project.getUsers().clear();
        project.addUser(user);
        int countAfterAdd = project.getUsers().size();
        project.removeUser(user);
        assertEquals(1, countAfterAdd);
        assertEquals(0, project.getUsers().size(), "checking users list is empty");
        assertFalse(project.containsUser(user), "checking user is not in list");
    }

    @Test
    void testRemoveUserFromMultipleUsers() {
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        User user3 = new User("user3", "password3", Role.GUEST);

        this.project.addUser(user1);
        this.project.addUser(user2);
        this.project.addUser(user3);

        this.project.removeUser(user2);

        assertEquals(2, this.project.getUsers().size(), "checking size is 2");
        assertTrue(this.project.containsUser(user1), "checking user1 still in list");
        assertFalse(this.project.containsUser(user2), "checking user2 removed");
        assertTrue(this.project.containsUser(user3), "checking user3 still in list");
    }

    @Test
    void testRemoveAllUsers() {
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);

        this.project.addUser(user1);
        this.project.addUser(user2);

        this.project.removeUser(user1);
        this.project.removeUser(user2);

        assertEquals(0, this.project.getUsers().size(), "checking all users removed");
    }

    @Test
    void testRemoveSameUserTwice() {
        User user = new User("testuser", "password", Role.SCIENTIST);

        project.addUser(user);
        project.removeUser(user);

        assertThrows(IllegalArgumentException.class, () -> project.removeUser(user), "checking removing same user twice throws exception");
    }
}