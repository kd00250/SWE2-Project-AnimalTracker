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
        return projectName;
    }

    public Collection<String> getScientistUsernames() {
        return scientistUsernames;
    }

    public Collection<Integer> getAnimalIds() {
        return animalIds;
    }

    @Override
    public void validateRequest() {
        if (scientistUsernames.isEmpty()) {
            throw new InvalidRequestException("There must be at least one scientist");
        }
        if (projectName == null) {
            throw new InvalidRequestException("projectName has to be set");
        }
        if (projectName.isEmpty()) {
            throw new InvalidRequestException("projectName has to be set");
        }

    }
}
