package edu.westga.cs3211.animaltracker.model.login.service;

import edu.westga.cs3211.animaltracker.model.login.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;

public class LocalLoginAuth implements AuthLoginService {
    @Override
    public LoginResponse login(LoginRequest request) {
        request.validateRequest();
        return new LoginResponse("1234", 1000);
    }

    @Override
    public boolean isValidToken(String token) {
        return true;
    }
}
