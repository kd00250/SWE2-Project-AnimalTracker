package edu.westga.cs3211.animaltracker.model.services;

import edu.westga.cs3211.animaltracker.model.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.request.LoginResponse;

public interface AuthLoginService {
    LoginResponse login(LoginRequest request);
}
