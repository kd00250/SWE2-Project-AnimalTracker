package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.*;

import java.util.List;

/**
 * The auth login service interface.
 */
public interface ServerService {

    /**
     * gets all the sightings for a specific animal.
     * @param request the request to be sent to the server
     * @return the list of sightings from the server
     */
    List<Sighting> getSightings(GetSightingRequest request);
    /**
     * adds the user to the server
     * @param request the request to send
     * @return if the sighting can be added or not
     */
    boolean addSighting(AddSightingRequest request);

    /**
     * gets the project from the server.
     * @param request the request to be sent
     * @return the project
     */
    Project requestSingleProject(GetSingleProjectRequest request);

    /**
     * adds the user to the server.
     * @param request the request to be sent
     * @return the status of the request
     */
    boolean addUser(AddUserRequest request);

    /**
     * Submits a login request and retrieves a response.
     * @param request the request
     * @return the response based on the request
     */
    LoginResponse login(LoginRequest request);

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
    List<Project> requestUserProjects(GetProjectRequest request);

    /**
     * Sends a request to the server to add a animal to the project.
     * @param request the request
     */
    void requestAddAnimal(AddAnimalRequest request);

    /**
     * Request all scientist in the system as long as user is a scientist or admin.
     * @param request the request
     * @return the scientist in the system
     */
    List<User> requestAllScientist(UserDataRequest request);

    /**
     * Requests all scientists from the server.
     * @param request the request
     * @return the users
     */
    List<User> requestAllScientistsFromServer(GetAllScientistsRequests request);

    /**
     * Adds a new project to the server.
     * @param request the request
     */
    void AddProject(AddProjectRequest request);

    /**
     * Deletes a project from the server.
     * @param request the project id
     */
    void deleteProject(DeleteProjectRequest request);

}
