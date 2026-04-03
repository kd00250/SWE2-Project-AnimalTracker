package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.client.Client;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddUserRequest;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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

    /**
     * adds user to server.
     * @param request the request
     * @return the request status
     */
    public boolean addUser(AddUserRequest request) {
        JSONObject response = this.client.send(request);
        var responseStatus = response.getString("status");
        return !responseStatus.equals("error");
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
    public List<Project> requestUserProjects(GetProjectRequest request) {
        JSONObject response = this.client.send(request);
        this.parseProjects(response);
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

    private List<Project> parseProjects(JSONObject response){
        List<Project> projects = new ArrayList<>();

        JSONArray projectArray = response.getJSONArray("projects");

        for (int i = 0; i < projectArray.length(); i++) {
            JSONObject projectJson = projectArray.getJSONObject(i);

            int id = projectJson.getInt("id");
            String name = projectJson.getString("name");

            Project project = new Project(
                    new ArrayList<>(),
                    name,
                    new ArrayList<>(),
                    id
            );

            projects.add(project);
        }

        return projects;
    }

    /**
     * closes the connection to the server.
     */
    public void close() {
        this.client.close();
    }
}