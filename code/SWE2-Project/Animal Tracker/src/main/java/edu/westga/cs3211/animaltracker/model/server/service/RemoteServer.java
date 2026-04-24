package edu.westga.cs3211.animaltracker.model.server.service;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.client.Client;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
     * gets all the sightings for a specific animal.
     *
     * @param request the request to be sent to the server
     * @return the list of sightings from the server
     */
    @Override
    public List<Sighting> getSightings(GetSightingRequest request) {
        JSONObject response = this.client.send(request);
        System.out.println(response);
        return this.buildSightingsFromResponse(response);
    }

    /**
     * adds the sighting to the server.
     *
     * @param request the request to send
     * @return if the sighting can be added or not
     */
    @Override
    public boolean addSighting(AddSightingRequest request) {
        JSONObject response = this.client.send(request);
        var responseStatus = response.getString("status");
        return !responseStatus.equals("error");
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

    /**
     * Sends the request to the server to add an animal.
     * @param request the request
     */
    @Override
    public void requestAddAnimal(AddAnimalRequest request) {
        JSONObject response = this.client.send(request);
    }

    /**
     * This method is for testing.
     * @param request the request
     * @return empty List
     */
    @Override
    public List<User> requestAllScientist(UserDataRequest request) {
        return List.of();
    }

    /**
     * Gets the list of scientists from the server.
     * @param request the request
     * @return the list of scientists from the server
     */
    @Override
    public List<User> requestAllScientistsFromServer(GetAllScientistsRequests request) {
        JSONObject response = this.client.send(request);
        this.buildScientistsFromJson(response);
        return this.buildScientistsFromJson(response);
    }

    @Override
    public void AddProject(AddProjectRequest request) {
        this.client.send(request);
    }

    @Override
    public void deleteProject(DeleteProjectRequest request) {
        JSONObject response = this.client.send(request);
    }

    private List<Project> parseProjects(JSONObject response) {
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
        return projects;
    }

    private Project buildProjectFromResponse(JSONObject response, String projectName, int projectId) {
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null");
        }

        Project project = new Project(new ArrayList<>(), projectName, new ArrayList<>(), projectId);

        JSONArray animalsArray = response.getJSONArray("animals");

        for (int i = 0; i < animalsArray.length(); i++) {
            JSONObject animalJson = animalsArray.getJSONObject(i);
            if (!this.hasValidAnimalValues(animalJson)) {
                System.out.println("Skipping invalid animal: " + animalJson);
                continue;
            }
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
        int tagID = animalJson.getInt("TagID");
        String description = animalJson.getString("Description");

        return new Animal(animalClass, height, weight, length, tagID, description);
    }

    private List<User> buildScientistsFromJson(JSONObject json) {
        if (json == null) {
            throw new IllegalArgumentException("JSON cannot be null");
        }

        List<User> users = new ArrayList<>();

        JSONArray userArray = json.getJSONArray("users");

        for (int i = 0; i < userArray.length(); i++) {
            JSONObject userJson = userArray.getJSONObject(i);

            String username = userJson.getString("username");
            String password = userJson.getString("password");

            users.add(new User(username, password, Role.SCIENTIST));
        }

        return users;
    }

    private boolean hasValidAnimalValues(JSONObject animalJson) {
        if (animalJson == null) {
            return false;
        }

        String classValue = animalJson.optString("Class", "").trim();
        if (classValue.isEmpty()) {
            return false;
        }

        try {
            AnimalClass.valueOf(classValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }

        if (animalJson.optDouble("Height", -1) <= 0) {
            return false;
        }
        if (animalJson.optDouble("Weight", -1) <= 0) {
            return false;
        }
        if (animalJson.optDouble("Length", -1) <= 0) {
            return false;
        }
        if (animalJson.optInt("TagID", -1) <= 0) {
            return false;
        }

        String description = animalJson.optString("Description", "").trim();
        return !description.isEmpty();
    }

    private List<Sighting> buildSightingsFromResponse(JSONObject response) {
        if (response == null) {
            throw new IllegalArgumentException("Response cannot be null");
        }

        List<Sighting> sightings = new ArrayList<>();
        System.out.println(response.toString());
        JSONArray sightingsArray = response.getJSONArray("sightings");

        for (int i = 0; i < sightingsArray.length(); i++) {
            JSONObject json = sightingsArray.getJSONObject(i);

            try {
                int tagID = json.getInt("animalTagID");
                String location = json.getString("location");
                double latitude = json.getDouble("latitude");
                double longitude = json.getDouble("longitude");
                String username = json.getString("username");

                LocalDateTime time = null;
                if (json.has("time") && !json.isNull("time")) {
                    var temp = json.getString("time");
                    time = LocalDateTime.parse(temp);
                }

                String notes = null;
                if (json.has("notes") && !json.isNull("notes")) {
                    notes = json.getString("notes");
                }

                Sighting sighting = new Sighting(tagID, location, latitude, longitude, time, notes, username);
                sightings.add(sighting);

            } catch (IllegalArgumentException e) {
                System.out.println("Skipping invalid sighting: " + json);
            }
        }

        return sightings;
    }

    /**
     * closes the connection to the server.
     */
    public void close() {
        this.client.close();
    }
}