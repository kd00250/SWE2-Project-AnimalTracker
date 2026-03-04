package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRequestAllAcientist {
    @Test
    void testNullRequest() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.requestAllScientist(null);
        });
    }

    @Test
    void testScientistUserGetsScientist() {
        DataStorage.getUsers().clear();
        DataStorage.getTokenMap().clear();
        var user = new User("1234", "1234", Role.SCIENTIST);
        var scientist1 = new User("141414", "162626", Role.SCIENTIST);
        var scientist2 = new User("141414", "16622626", Role.SCIENTIST);
        var scientist3 = new User("1414234614", "162626", Role.SCIENTIST);
        DataStorage.getUsers().add(scientist1);
        DataStorage.getUsers().add(scientist2);
        DataStorage.getUsers().add(scientist3);
        var server = new LocalServer();

        var token = DataStorage.generateTokenForUser(user);
        var request = new UserDataRequest(token);
        var scientists = server.requestAllScientist(request);
        System.out.println(scientists);
        assertTrue(scientists.contains(scientist1));
        assertTrue(scientists.contains(scientist2));
        assertTrue(scientists.contains(scientist3));
        assertEquals(3, scientists.size());
    }

    @Test
    void testScientistUserGetsScientistWithNonScientist() {
        DataStorage.getUsers().clear();
        DataStorage.getTokenMap().clear();
        var user = new User("1234", "1234", Role.SCIENTIST);
        var scientist1 = new User("141414", "162626", Role.SCIENTIST);
        var scientist2 = new User("141414", "16622626", Role.SCIENTIST);
        var scientist3 = new User("1414234614", "162626", Role.SCIENTIST);
        var guest = new User("1235555", "151515", Role.GUEST);
        DataStorage.getUsers().add(scientist1);
        DataStorage.getUsers().add(scientist2);
        DataStorage.getUsers().add(scientist3);
        DataStorage.getUsers().add(guest);
        var server = new LocalServer();

        var token = DataStorage.generateTokenForUser(user);
        var request = new UserDataRequest(token);
        var scientists = server.requestAllScientist(request);
        System.out.println(scientists);
        assertTrue(scientists.contains(scientist1));
        assertTrue(scientists.contains(scientist2));
        assertTrue(scientists.contains(scientist3));
        assertEquals(3, scientists.size());
    }

    @Test
    void testAdminUserGetsScientist() {
        DataStorage.getUsers().clear();
        DataStorage.getTokenMap().clear();
        var user = new User("1234", "1234", Role.ADMIN);
        var scientist1 = new User("141414", "162626", Role.SCIENTIST);
        var scientist2 = new User("141414", "16622626", Role.SCIENTIST);
        var scientist3 = new User("1414234614", "162626", Role.SCIENTIST);
        var guest = new User("1235555", "151515", Role.GUEST);
        DataStorage.getUsers().add(scientist1);
        DataStorage.getUsers().add(scientist2);
        DataStorage.getUsers().add(scientist3);
        DataStorage.getUsers().add(guest);
        var server = new LocalServer();

        var token = DataStorage.generateTokenForUser(user);
        var request = new UserDataRequest(token);
        var scientists = server.requestAllScientist(request);
        System.out.println(scientists);
        assertTrue(scientists.contains(scientist1));
        assertTrue(scientists.contains(scientist2));
        assertTrue(scientists.contains(scientist3));
        assertEquals(3, scientists.size());
    }

    @Test
    void testNonAdminOrScientistRequestData() {
        DataStorage.getUsers().clear();
        DataStorage.getTokenMap().clear();
        var user = new User("1234", "1234", Role.GUEST);
        var scientist1 = new User("141414", "162626", Role.SCIENTIST);
        var scientist2 = new User("141414", "16622626", Role.SCIENTIST);
        var scientist3 = new User("1414234614", "162626", Role.SCIENTIST);
        var guest = new User("1235555", "151515", Role.GUEST);
        DataStorage.getUsers().add(scientist1);
        DataStorage.getUsers().add(scientist2);
        DataStorage.getUsers().add(scientist3);
        DataStorage.getUsers().add(guest);
        var server = new LocalServer();

        var token = DataStorage.generateTokenForUser(user);
        var request = new UserDataRequest(token);
        assertThrows(InvalidRequestException.class, () -> {
            server.requestAllScientist(request);
        });
    }
}
