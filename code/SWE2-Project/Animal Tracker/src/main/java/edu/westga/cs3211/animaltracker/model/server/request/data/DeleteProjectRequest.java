package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.DELETE_PROJECT_REQUEST;
import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.GET_PROJECT_REQUEST;

/**
 * the delete project request class.
 */
public class DeleteProjectRequest extends Request {
    private int projectID;

    /**
     * creates a new instance of get single project request.
     * @param id the id
     */
    public DeleteProjectRequest(int id) {
        this.projectID = id;
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
        if (this.projectID < 0) {
            throw new InvalidRequestException("Project id is null");
        }
    }

    /**
     * returns the json string to be sent.
     * @return the json to be sent
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", DELETE_PROJECT_REQUEST);
        json.put("project id", this.getProjectID());
        return json;
    }
}
