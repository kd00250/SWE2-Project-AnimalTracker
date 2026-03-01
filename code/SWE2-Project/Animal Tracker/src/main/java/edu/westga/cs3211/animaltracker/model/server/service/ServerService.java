package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;

import java.util.Collection;
import java.util.List;

/**
 * The auth login service interface.
 */
public interface ServerService {
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

    /**
     * Request the role for a given user.
     * @param request  the request
     * @return the role of the user
     */
    Role requestUserRole(UserDataRequest request);

    /**
     * Gets all projects for the user associated with the request token.
     *
     * @param request the get all projects for user request
     * @return a list of projects associated with the user
     */
    List<Project> requestUserProjects(UserDataRequest request);

    Collection<User> requestAllScientist(UserDataRequest request);
    void AddProject(AddProjectRequest request);
    void deleteProject(int projectId);

}
