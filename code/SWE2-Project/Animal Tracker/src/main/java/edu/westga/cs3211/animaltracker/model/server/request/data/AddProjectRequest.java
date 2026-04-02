package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import java.util.Collection;

/**
 * The AddProjectRequest class.
 */
public class AddProjectRequest extends Request {
    private final String projectName;
    private final Collection<String> scientistUsernames;
    private final Collection<Integer> animalIds;

    /**
     * Instantiates a new project add request.
     * @param projectName the project name
     * @param scientistUsernames the scientist usernames
     * @param animalIds the animal id's
     */
    public AddProjectRequest(String projectName, Collection<String> scientistUsernames, Collection<Integer> animalIds) {
        this.projectName = projectName;
        this.scientistUsernames = scientistUsernames;
        this.animalIds = animalIds;
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
        return null;
    }
}
