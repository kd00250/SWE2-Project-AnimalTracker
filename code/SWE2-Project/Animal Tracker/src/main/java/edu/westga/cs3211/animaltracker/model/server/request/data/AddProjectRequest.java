package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;

import java.util.Collection;

public class AddProjectRequest extends Request {
    private String projectName;
    private Collection<String> scientistUsernames;
    private Collection<Integer> animalIds;

    public AddProjectRequest(String projectName, Collection<String> scientistUsernames, Collection<Integer> animalIds) {
        this.projectName = projectName;
        this.scientistUsernames = scientistUsernames;
        this.animalIds = animalIds;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public Collection<String> getScientistUsernames() {
        return this.scientistUsernames;
    }

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
}
