package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_PROJECT_REQUEST;

/**
 * the get single project request class.
 */
public class GetSingleProjectRequest extends Request {
    private String token;
    private String projectName;
    private int projectID;

    /**
     * creates a new instance of get single project request.
     * @param token the token
     * @param projectName the project name
     * @param id the id
     */
    public GetSingleProjectRequest(String token, String projectName, int id) {
        this.token = token;
        this.projectName = projectName;
        this.projectID = id;
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
     * Validates the request.
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
     * returns the json string to be sent.
     * @return the json to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", GET_PROJECT_REQUEST);
        json.put("token", this.getToken());
        json.put("project name", this.getProjectName());
        json.put("project id", this.getProjectID());
        return json;
    }
}
