package edu.westga.cs3211.animaltracker.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.security.InvalidParameterException;

/**
 * The CreateProjectViewModel.
 *
 * @author mrocker1
 */
public class CreateProjectViewModel {

    private StringProperty projectName;
    private StringProperty projectLocation;

    /**
     * Instantiates a new CreateProjectViewModel.
     */
    public CreateProjectViewModel() {
        this.projectName = new SimpleStringProperty("");
        this.projectLocation = new SimpleStringProperty("");
    }

    /**
     * Gets the ProjectNameProperty.
     *
     * @return the ProjectNameProperty
     */
    public StringProperty getProjectNameProperty() {
        return this.projectName;
    }

    /**
     * Sets the ProjectNameProperty.
     *
     * @param projectName the new ProjectName
     * @pre !projectName.isBlank
     * @post getProjectNameProperty().getValue().equals(projectName)
     */
    public void setProjectName(String projectName) {
        if (projectName == null) {
            throw new InvalidParameterException("The Project Name cannot be null");
        }
        if (projectName.isBlank()) {
            throw new InvalidParameterException("The Project Name cannot be Empty or contain Only Whitespace");
        }

        this.projectName.set(projectName);
    }

    /**
     * Gets the ProjectLocationProperty.
     *
     * @return the ProjectLocationProperty
     */
    public StringProperty getProjectLocationProperty() {
        return this.projectLocation;
    }

    /**
     * Sets the ProjectLocationProperty.
     *
     * @param projectLocation the new ProjectLocation
     * @pre !projectName.isBlank
     * @post getProjectNameProperty().getValue().equals(projectName)
     */
    public void setProjectLocation(String projectLocation) {
        if (projectLocation == null) {
            throw new InvalidParameterException("The Project Name cannot be null");
        }
        if (projectLocation.isBlank()) {
            throw new InvalidParameterException("The Project Name cannot be Empty or contain Only Whitespace");
        }

        this.projectLocation.set(projectLocation);
    }
}
