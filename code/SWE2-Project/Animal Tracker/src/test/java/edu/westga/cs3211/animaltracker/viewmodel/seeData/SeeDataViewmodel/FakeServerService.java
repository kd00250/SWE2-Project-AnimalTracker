package edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewmodel;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.*;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;

import java.util.ArrayList;
import java.util.List;

public class FakeServerService implements ServerService {
    private final ArrayList<Sighting> sightings;
    private boolean throwsException = false;
    public FakeServerService() {
        this.sightings = new ArrayList<>();
    }
    public void setShouldThrow(boolean shouldThrow) {
        this.throwsException = shouldThrow;
    }
    @Override
    public List<Sighting> getSightings(GetSightingRequest request) {
        if (this.throwsException) {
            throw new IllegalArgumentException("Invalid Server Request");
        }
        return this.sightings;
    }

    @Override
    public boolean addSighting(AddSightingRequest request) {
        this.sightings.add(request.getSighting());
        return true;
    }

    @Override
    public Project requestSingleProject(GetSingleProjectRequest request) {
        return null;
    }

    @Override
    public boolean addUser(AddUserRequest request) {
        return false;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public Role requestUserRole(UserDataRequest request) {
        return null;
    }

    @Override
    public List<Project> requestUserProjects(GetProjectRequest request) {
        return List.of();
    }

    @Override
    public void requestAddAnimal(AddAnimalRequest request) {

    }

    @Override
    public List<User> requestAllScientist(UserDataRequest request) {
        return List.of();
    }

    @Override
    public List<User> requestAllScientistsFromServer(GetAllScientistsRequests request) {
        return List.of();
    }

    @Override
    public void AddProject(AddProjectRequest request) {

    }

    @Override
    public void deleteProject(DeleteProjectRequest request) {

    }
}
