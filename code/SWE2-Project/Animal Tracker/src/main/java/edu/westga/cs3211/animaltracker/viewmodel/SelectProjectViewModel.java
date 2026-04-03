package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetProjectResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import java.util.List;

/**
 * The select project view model class.
 *
 * @author mrocker1
 */
public class SelectProjectViewModel {
    private LoginResponse authSession;
    private ServerService serverService;
    private final ListProperty<Project> projects;
    private final ObjectProperty<Project> selectedProject;

    /**
     * Instantiates a new select project view model.
     */
    public SelectProjectViewModel() {
        this.projects = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.selectedProject = new SimpleObjectProperty<>();
    }

    /**
     * Sets the session for this view model.
     *
     * @param session the user's session
     * @param server the server service
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
    }

    /**
     * Gets the projects list property.
     *
     * @return the projects list property
     */
    public ListProperty<Project> projectsProperty() {
        return this.projects;
    }

    /**
     * Gets the selected project property.
     *
     * @return the selected project property
     */
    public ObjectProperty<Project> selectedProjectProperty() {
        return this.selectedProject;
    }

    /**
     * Gets the currently selected project.
     *
     * @return the selected project, or null if none selected
     */
    public Project getSelectedProject() {
        return this.selectedProject.get();
    }

    /**
     * Loads all projects for the logged-in user using the server service.
     */
    public void loadProjects() {
        //var request = new UserDataRequest(this.authSession.getToken());
        GetProjectRequest request = new GetProjectRequest(this.authSession.getToken());

        List<Project> userProjects = this.serverService.requestUserProjects(request);

        this.projects.setAll(userProjects);
    }

    /**
     * Refreshes the project list.
     */
    public void refreshProjects() {
        this.loadProjects();
    }

    /**
     * Deletes the currently selected project.
     *
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteSelectedProject() {
        Project project = this.getSelectedProject();
        if (project == null) {
            return false;
        }
        edu.westga.cs3211.animaltracker.model.DataStorage.getProjects().remove(project.getId());

        this.refreshProjects();

        return true;
    }

    /**
     * Gets the session information.
     *
     * @return the session
     */
    public LoginResponse getSession() {
        return this.authSession;
    }

    /**
     * Gets the server service.
     *
     * @return the server service
     */
    public ServerService getServerService() {
        return this.serverService;
    }
}