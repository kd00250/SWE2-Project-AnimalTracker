package edu.westga.cs3211.animaltracker.model.server.request.auth.loginrequest;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateRequestTest {

    @Test
    void testValidValidation() {
        var username = "username";
        var password = "password";
        var request =  new LoginRequest(username, password);
        assertDoesNotThrow(request::validateRequest);
    }
    @Test
    void validateRequestThrowsWhenNullUsername() {
        var request = new LoginRequest(null, "1234");
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
    @Test
    void validateRequestThrowsWhenNullPassword() {
        var request = new LoginRequest("tim", null);
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
    @Test
    void validateRequestThrowsWhenEmptyUsername() {
        var request = new LoginRequest("", "1234");
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
    @Test
    void validateRequestThrowsWhenEmptyPassword() {
        var request = new LoginRequest("1234", "");
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }




}
