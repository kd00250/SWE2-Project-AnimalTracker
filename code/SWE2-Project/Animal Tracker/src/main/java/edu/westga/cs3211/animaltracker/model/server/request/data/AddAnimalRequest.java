package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

/**
 * the add animal request class.
 */
public class AddAnimalRequest extends Request {
    private String token;
    private String projectName;
    private int projectID;
    private Animal animal;

    public AddAnimalRequest(String token, String projectName, int projectID, Animal animal) {
        this.token = token;
        this.projectName = projectName;
        this.projectID = projectID;
    }

    /**
     *
     * @return
     */
    public Animal getAnimal() {
        return this.animal;
    }

    /**
     * Gets the user token.
     * @return the token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * gets the project name.
     * @return the project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Gets the project id.
     * @return the project id
     */
    public int getProjectID() {
        return this.projectID;
    }

    /**
     * validates the request.
     */
    @Override
    public void validateRequest() {
        if (this.token == null) {
            throw new InvalidRequestException("Token is null");
        }
        if (this.projectName == null) {
            throw new InvalidRequestException("Project name is null");
        }
    }

    /**
     * gets the json string to be sent.
     * @return the json string to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("token", this.getToken());
        json.put("project name", this.getProjectName());
        json.put("project id", this.getProjectID());
        return json;
    }
}
