package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static edu.westga.cs3211.animaltracker.model.DataStorage.getUsers;

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

    @Override
    public List<Project> requestUserProjects(UserDataRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        request.validateRequest();

        if (!DataStorage.tokenExist(request.getToken())) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        User user = DataStorage.getUserByToken(request.getToken());
        List<Project> userProjects = new ArrayList<>();

        for (Project project : DataStorage.getProjects().values()) {
            if (project.containsUser(user)) {
                userProjects.add(project);
            }
        }
        return userProjects;
    }

    @Override
    public Collection<User> requestAllScientist(UserDataRequest request) {
        var role = this.requestUserRole(request);
        if (role == Role.SCIENTIST || role == Role.ADMIN) {
            Collection<User> scientist = new ArrayList<>();
            for (User user : getUsers()) {
                if (user.getRole() == Role.SCIENTIST) {
                    scientist.add(user);
                }
            }
            return scientist;
        } else {
            throw new InvalidRequestException("Invalid role");
        }
    }
}
