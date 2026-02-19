package edu.westga.cs3211.animaltracker.model.login.service;

import edu.westga.cs3211.animaltracker.model.login.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;

/**
 * The auth login service interface.
 */
public interface AuthLoginService {
    /**
     * Submits a login request and retrieves a response.
     * @param request the request
     * @return the response based on the request
     */
    LoginResponse login(LoginRequest request);

    /**
     * Checks if a given token is valid.
     * @param token the token
     * @return true if valid, otherwise false
     */
    boolean isValidToken(String token);
}
