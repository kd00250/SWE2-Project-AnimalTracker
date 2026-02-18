package edu.westga.cs3211.animaltracker.model.services;

import edu.westga.cs3211.animaltracker.model.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.request.LoginResponse;

public class LocalLoginAuth implements AuthLoginService {
    @Override
    public LoginResponse login(LoginRequest request) {
        return new LoginResponse("1234", 1000);
    }
}
