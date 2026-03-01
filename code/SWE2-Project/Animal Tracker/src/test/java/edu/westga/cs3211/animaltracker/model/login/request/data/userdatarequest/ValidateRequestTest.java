package edu.westga.cs3211.animaltracker.model.login.request.data.userdatarequest;

import edu.westga.cs3211.animaltracker.model.login.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.login.request.data.UserDataRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateRequestTest {
    @Test
    void testValidRequest() {
        var request = new UserDataRequest("1234");
        assertDoesNotThrow(request::validateRequest);
    }

    @Test
    void testNullToken() {
        var request = new UserDataRequest(null);
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }

    @Test
    void testEmptyToken() {
        var request = new UserDataRequest("");
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
}
