package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.ADD_PROJECT_REQUEST;
import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.CREATE_PROJECT_REQUEST;

/**
 * The AddProjectRequest class.
 */
public class AddProjectRequest extends Request {
    private final String projectName;
    private final Collection<String> scientistUsernames;
    private final Collection<Integer> animalIds;
    private final String token;

    /**
     * Instantiates a new project add request.
     * @param projectName the project name
     * @param scientistUsernames the scientist usernames
     * @param animalIds the animal id's
     * @param token the token
     */
    public AddProjectRequest(String projectName, Collection<String> scientistUsernames, Collection<Integer> animalIds, String token) {
        this.projectName = projectName;
        this.scientistUsernames = scientistUsernames;
        this.animalIds = animalIds;
        this.token = token;
    }

    /**
     * Gets the project name.
     * @return the project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Gets the scientist usernames.
     * @return the scientist usernames
     */
    public Collection<String> getScientistUsernames() {
        return this.scientistUsernames;
    }

    /**
     * Gets the animal id's.
     * @return the animal id's
     */
    public Collection<Integer> getAnimalIds() {
        return this.animalIds;
    }

    /**
     * gets the token from the request.
     * @return the token from the request
     */
    public String getToken() {
        return this.token;
    }

    @Override
    public void validateRequest() {
        if (this.projectName == null) {
            throw new InvalidRequestException("projectName is null");
        }
        if (this.projectName.isEmpty()) {
            throw new InvalidRequestException("projectName is empty");
        }
        if (this.scientistUsernames == null) {
            throw new InvalidRequestException("scientistUsernames is null");
        }
        if (this.animalIds == null) {
            throw new InvalidRequestException("animalIds is null");
        }

    }

    /**
     * changes project to json to be sent to the storage.
     * @return the json to be sent.
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", CREATE_PROJECT_REQUEST);
        json.put("token", this.getToken());
        json.put("projectName", this.getProjectName());

        json.put("scientists", this.buildStringArray(this.getScientistUsernames()));
        json.put("animals", this.buildIntegerArray(this.getAnimalIds()));

        return json;
    }

    private JSONArray buildStringArray(Collection<String> values) {
        JSONArray array = new JSONArray();

        for (String value : values) {
            array.put(value);
        }

        return array;
    }

    private JSONArray buildIntegerArray(Collection<Integer> values) {
        JSONArray array = new JSONArray();

        for (Integer value : values) {
            array.put(value);
        }

        return array;
    }
}
