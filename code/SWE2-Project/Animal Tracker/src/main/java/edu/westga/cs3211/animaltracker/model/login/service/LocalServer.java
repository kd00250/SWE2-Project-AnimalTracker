package edu.westga.cs3211.animaltracker.model.login.service;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.request.data.UserDataRequest;

/**
 * The local login auth class.
 */
public class LocalServer implements ServerService {
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
        var matchingUser = DataStorage.getUserByUsername(request.getUsername());
        if (matchingUser != null) {
            if (matchingUser.getPassword().equals(request.getPassword())) {
                var token = DataStorage.generateTokenForUser(matchingUser);
                return new LoginResponse(token, DEFAULT_TIMEOUT);
            }
        }
        return null;
    }

    @Override
    public boolean isValidToken(String token) {
        if (token != null) {
            return !token.isEmpty();
        }
        return false;
    }

    @Override
    public Role requestUserRole(UserDataRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("UserDataRequest is null");
        }
        request.validateRequest();
        if (DataStorage.tokenExist(request.getToken())) {
            var user = DataStorage.getUserByToken(request.getToken());
            return user.getRole();
        }
        return null;
    }
}
