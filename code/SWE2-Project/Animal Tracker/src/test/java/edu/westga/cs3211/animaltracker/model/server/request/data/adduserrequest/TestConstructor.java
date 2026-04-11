package edu.westga.cs3211.animaltracker.model.server.request.data.adduserrequest;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestConstructor {
    @Test
    void testConstructorAndGettersWithValidValues() {
        AddUserRequest request = new AddUserRequest("kenny", "1234", Role.SCIENTIST);

        assertEquals("kenny", request.getUsername());
        assertEquals("1234", request.getPassword());
        assertEquals(Role.SCIENTIST, request.getRole());
    }
}
