package edu.westga.cs3211.animaltracker.model.login.service;

import edu.westga.cs3211.animaltracker.model.login.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;

public interface AuthLoginService {
    LoginResponse login(LoginRequest request);
    boolean isValidToken(String token);
}
