package edu.westga.cs3211.animaltracker.model.login.request.data;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.login.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.login.request.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Request to get all projects for a specific user.
 *
 * @author mrocker1
 */
public final class GetAllProjectsForUserRequest extends Request {
    private String token;

    /**
     * Instantiates a new get all projects for user request.
     *
     * @param token the user's authentication token
     */
    public GetAllProjectsForUserRequest(String token) {
        this.token = token;
    }

    /**
     * Gets the user token.
     *
     * @return the token
     */
    public String getToken() {
        return this.token;
    }

    @Override
    public void validateRequest() {
        if (this.token == null) {
            throw new InvalidRequestException("Token is null");
        }
        if (this.token.isEmpty()) {
            throw new InvalidRequestException("Token is empty");
        }
        if (!DataStorage.tokenExist(this.token)) {
            throw new InvalidRequestException("Token is invalid");
        }
    }

    /**
     * Gets all projects associated with the user identified by the token.
     *
     * @return a list of all projects the user is associated with
     * @throws InvalidRequestException if the request is invalid
     */
    public List<Project> getAllProjectsForUser() {
        this.validateRequest();

        User user = DataStorage.getUserByToken(this.token);
        if (user == null) {
            return new ArrayList<>();
        }

        List<Project> userProjects = new ArrayList<>();

        for (Project project : DataStorage.getProjects().values()) {
            if (project.containsUser(user)) {
                userProjects.add(project);
            }
        }

        return userProjects;
    }
}