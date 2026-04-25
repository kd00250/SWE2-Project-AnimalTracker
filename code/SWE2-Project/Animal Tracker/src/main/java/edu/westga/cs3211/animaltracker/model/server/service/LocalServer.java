package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.*;

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

    /**
     * gets all the sightings for a specific animal.
     * note that this is a method for testing only as we do not use the local server anymore
     *
     * @param request the request to be sent to the server
     * @return the list of sightings
     */
    @Override
    public List<Sighting> getSightings(GetSightingRequest request) {
        return null;
    }

    /**
     * This method is for testing purposes only as we do not use our local storage for add sightings.
     *
     * @param request the request to send
     * @return if the sighting can be added or not
     */
    @Override
    public boolean addSighting(AddSightingRequest request) {
        if (request != null) {
            return DataStorage.getAnimalById(request.getSighting().getAnimalTagID()) != null;
        }
        return false;
    }

    /**
     * requests the info for a single project.
     * @param request the request to be sent
     * @return null (this is for testing)
     */
    @Override
    public Project requestSingleProject(GetSingleProjectRequest request) {
        return DataStorage.getProjects().get(request.getProjectID());
    }

    /**
     * this method is for testing purpose only.
     * @param request the request to be sent
     * @return null
     */
    @Override
    public boolean addUser(AddUserRequest request) {
        User user = new User(request.getUsername(), request.getPassword(), request.getRole());
        if (!DataStorage.isUsernameAvailable(user.getUsername())) {
            return false;
        }
        DataStorage.getUsers().add(user);
        return true;
    }

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
    public List<Project> requestUserProjects(GetProjectRequest request) {
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

    /**
     * This is for testing only.
     * @param request the request
     */
    @Override
    public void requestAddAnimal(AddAnimalRequest request) {
        DataStorage.getAnimals().put(request.getAnimal().getId(), request.getAnimal());
        DataStorage.getProjects().get(request.getProjectID()).addAnimal(request.getAnimal());
    }

    @Override
    public List<User> requestAllScientist(UserDataRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        var role = this.requestUserRole(request);
        if (role == Role.SCIENTIST || role == Role.ADMIN) {
            List<User> scientist = new ArrayList<>();
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

    /**
     * This method is for testing purposes only.
     * @param request the request
     * @return nothing this method is for testing
     */
    @Override
    public List<User> requestAllScientistsFromServer(GetAllScientistsRequests request) {
        var users = DataStorage.getUsers();
        var scientist = new ArrayList<User>();
        for (User user : users) {
            if (user.getRole() == Role.SCIENTIST) {
                scientist.add(user);
            }
        }
        return scientist;
    }

    @Override
    public void AddProject(AddProjectRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        var scientist = this.getScientistFromUsername(request.getScientistUsernames());
        var animals = this.getAnimalsFromId(request.getAnimalIds());
        var project = new Project(scientist, request.getProjectName(), animals);
        DataStorage.addProject(project);
    }

    @Override
    public void deleteProject(DeleteProjectRequest request) {

        DataStorage.deleteProjectIfExist(request.getProjectID());
    }

    private List<Animal> getAnimalsFromId(Collection<Integer> ids) {
        List<Animal> animals = new ArrayList<>();
        for (Integer id : ids) {
            var animal = DataStorage.getAnimalById(id);
            animals.add(animal);

        }
        return animals;
    }

    private List<User> getScientistFromUsername(Collection<String> usernames) {
        List<User> scientists = new ArrayList<>();
        for (String username : usernames) {
            var scientist = DataStorage.getUserByUsername(username);
            if (scientist.getRole() == Role.SCIENTIST) {
                scientists.add(scientist);
            }
        }
        return scientists;
    }
}
