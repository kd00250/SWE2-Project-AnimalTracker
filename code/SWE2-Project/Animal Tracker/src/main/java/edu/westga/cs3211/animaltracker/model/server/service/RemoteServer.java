package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.client.Client;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.*;
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
     * requests the info for a single project.
     * @param request the request to be sent
     * @return the project requested
     */
    @Override
    public Project requestSingleProject(GetSingleProjectRequest request) {
        String projectName = request.getProjectName();
        int projectID = request.getProjectID();
        JSONObject response = this.client.send(request);
        return this.buildProjectFromResponse(response, projectName, projectID);
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
        return this.parseProjects(response);
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
        System.out.print(response.toString());

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
        System.out.println(projects.getFirst().getName() + " " + projects.getFirst().getId());
        return projects;
    }

    private Project buildProjectFromResponse(JSONObject response, String projectName, int projectId) {
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null");
        }

        Project project = new Project(new ArrayList<>(), projectName, new ArrayList<>(), projectId);

        JSONObject projectJson = response.getJSONObject("project");
        JSONArray animalsArray = projectJson.getJSONArray("animals");

        for (int i = 0; i < animalsArray.length(); i++) {
            JSONObject animalJson = animalsArray.getJSONObject(i);
            Animal animal = this.buildAnimal(animalJson);
            project.addAnimal(animal);
        }

        return project;
    }

    private Animal buildAnimal(JSONObject animalJson) {
        if (animalJson == null) {
            throw new IllegalArgumentException("Animal JSON cannot be null");
        }

        AnimalClass animalClass = AnimalClass.valueOf(animalJson.getString("Class"));

        double height = animalJson.getDouble("Height");
        double weight = animalJson.getDouble("Weight");
        double length = animalJson.getDouble("Length");
        int tagID = animalJson.getInt("tagID");
        String description = animalJson.getString("Description");

        return new Animal(animalClass, height, weight, length, tagID, description);
    }

    /**
     * closes the connection to the server.
     */
    public void close() {
        this.client.close();
    }
}