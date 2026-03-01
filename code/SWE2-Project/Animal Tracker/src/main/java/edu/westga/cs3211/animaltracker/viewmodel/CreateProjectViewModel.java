package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The CreateProjectViewModel.
 *
 * @author mrocker1
 */
public class CreateProjectViewModel {

    private StringProperty projectName;
    private StringProperty projectLocation;
    private ArrayList<User> addedScientist;

    /**
     * Instantiates a new CreateProjectViewModel.
     */
    public CreateProjectViewModel() {
        this.projectName = new SimpleStringProperty("");
        this.projectLocation = new SimpleStringProperty("");
        this.addedScientist = new ArrayList<User>();
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
     * gets the list of added scientists.
     *
     * @return the list of added scientists
     */
    public ArrayList<User> getAddedScientist() {
        return this.addedScientist;
    }

    /**
     * get all the available Scientists.
     *
     * @return the available scientist
     */
    public ArrayList<User> getAvailableScientists() {
        ArrayList<User> availableUsers = new ArrayList<>();
        for (User currentUser : DataStorage.getUsers()) {
            if (currentUser.getRole().equals(Role.SCIENTIST)) {
                availableUsers.add(currentUser);
            }
        }
        return availableUsers;
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

    /**
     * adds a scientist to a project.
     *
     * @param user to add to the project
     */
    public void addScientistToProject(User user) {
        if (this.addedScientist.contains(user)) {
            throw new IllegalArgumentException("Error the scientist selected has already been added. Select another and try again.");
        }
        this.addedScientist.add(user);
    }

    /**
     * creates a new project based on the information entered.
     *
     * @param name the name
     * @param users the users
     */
    public void createProject(String name, ArrayList<User> users) {
        ArrayList<Scientist> dummyList = new ArrayList<>();
        ArrayList<Animal> emptyList = new ArrayList<>();
        Project project = new Project(name, dummyList, emptyList, users);
    }
}
