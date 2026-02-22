package edu.westga.cs3211.animaltracker.model.login.service;

import edu.westga.cs3211.animaltracker.model.login.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;

/**
 * The local login auth class.
 */
public class LocalLoginAuth implements AuthLoginService {
    /**
     * The default timeout for a local login.
     */
    public static final int DEFAULT_TIMEOUT = 1000;

    @Override
    public LoginResponse login(LoginRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("LoginRequest is null");
        }
        request.validateRequest();
        return new LoginResponse("1234", DEFAULT_TIMEOUT);
    }

    @Override
    public boolean isValidToken(String token) {
        if (token != null) {
            return !token.isEmpty();
        }
        return false;
    }
}
