package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.client.Client;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import org.json.JSONObject;

import java.util.Collection;
import java.util.List;

/**
 * the remoteServer class.
 */
public class RemoteServer implements ServerService {

    private final Client client;

    /**
     * creates a new instance of client.
     */
    public RemoteServer() {
        this.client = new Client();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        JSONObject response = this.client.send(request);

        String token = response.optString("token", null);
        int timeout = 1000;
        if (token == null) {
            return null;
        }

        return new LoginResponse(token, timeout);
    }

    @Override
    public Role requestUserRole(UserDataRequest request) {
        JSONObject response = this.client.send(request);
        String roleText = response.getString("role");
        return Role.valueOf(roleText);
    }

    @Override
    public List<Project> requestUserProjects(UserDataRequest request) {
        JSONObject response = this.client.send(request);
        return List.of();
    }

    @Override
    public Collection<User> requestAllScientist(UserDataRequest request) {
        JSONObject response = this.client.send(request);
        return List.of();
    }

    @Override
    public void AddProject(AddProjectRequest request) {
        this.client.send(request);
    }

    @Override
    public void deleteProject(int projectId) {
    }

    /**
     * closes the connection to the server.
     */
    public void close() {
        this.client.close();
    }
}