package edu.westga.cs3211.animaltracker.model.server.request.data.adduserrequest;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestValidateRequest {

    @Test
    void testValidateRequestWithValidValuesDoesNotThrowException() {
        AddUserRequest request = new AddUserRequest("kenny", "1234", Role.SCIENTIST);

        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testValidateRequestWithNullUsernameThrowsException() {
        AddUserRequest request = new AddUserRequest(null, "1234", Role.SCIENTIST);

        InvalidRequestException exception =
                assertThrows(InvalidRequestException.class, request::validateRequest);

        assertEquals("Username is null", exception.getMessage());
    }

    @Test
    void testValidateRequestWithNullPasswordThrowsException() {
        AddUserRequest request = new AddUserRequest("kenny", null, Role.SCIENTIST);

        InvalidRequestException exception =
                assertThrows(InvalidRequestException.class, request::validateRequest);

        assertEquals("Password is null", exception.getMessage());
    }

    @Test
    void testValidateRequestWithNullRoleThrowsException() {
        AddUserRequest request = new AddUserRequest("kenny", "1234", null);

        InvalidRequestException exception =
                assertThrows(InvalidRequestException.class, request::validateRequest);

        assertEquals("Role is null", exception.getMessage());
    }
}
