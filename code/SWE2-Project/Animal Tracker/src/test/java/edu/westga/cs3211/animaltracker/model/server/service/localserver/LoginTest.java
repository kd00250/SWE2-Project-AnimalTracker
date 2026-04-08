package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @BeforeEach
    void setUp() {
        DataStorage.getUsers().clear();
        DataStorage.getProjects().clear();
        DataStorage.getUsernameMap().clear();
    }

    @Test
    void testValidLoginAttempt() {
        var username = "Bob";
        var password = "1234";
        var user = new User(username, password, Role.SCIENTIST);
        DataStorage.getUsers().add(user);
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNotNull(response.getToken());
        assertEquals(LocalServer.DEFAULT_TIMEOUT, response.getTimeout());
    }

    @Test
    void testNullRequestOnLogin() {
        var auth = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> auth.login(null));
    }

    @Test
    void testUserFoundWrongPassword() {
        var username = "Bob";
        var password = "12345";
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNull(response);
    }

    @Test
    void testUserNotFound() {
        var username = "1616161616";
        var password = "12345";
        var request = new LoginRequest(username, password);
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNull(response);
    }

    @Test
    void testUserExistButPasswordWrong() {
        var user0 = new User("Bobby", "12344", Role.SCIENTIST);
        DataStorage.reset();
        DataStorage.getUsers().add(user0);
        DataStorage.getUsernameMap().clear();
        DataStorage.getUsernameMap().put(user0.username(), user0);
        var request = new LoginRequest(user0.username(), "!!!!");
        var auth = new LocalServer();
        var response = auth.login(request);
        assertNull(response);
    }
}
