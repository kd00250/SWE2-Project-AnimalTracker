package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetSingleProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.*;

import java.util.List;

/**
 * the view project data view model.
 */
public class ViewProjectDataViewModel {
    private LoginResponse authSession;
    private ServerService serverService;
    private final ObjectProperty<AnimalClass> animalClass;
    private final ObjectProperty<Animal> animal;
    private final ObjectProperty<Project> project;
    private final StringProperty projectName;
    private final DoubleProperty height;
    private final DoubleProperty weight;
    private final DoubleProperty length;
    private final IntegerProperty tagID;
    private final StringProperty description;

    /**
     * creates a new instance of view project view model.
     */
    public ViewProjectDataViewModel() {
        this.animal = new SimpleObjectProperty<>();
        this.animalClass = new SimpleObjectProperty<>();
        this.project = new SimpleObjectProperty<>();
        this.projectName = new SimpleStringProperty("");
        this.height = new SimpleDoubleProperty();
        this.weight = new SimpleDoubleProperty();
        this.length = new SimpleDoubleProperty();
        this.tagID = new SimpleIntegerProperty();
        this.description = new SimpleStringProperty("");
    }

    /**
     * gets the project name property.
     *
     * @return the project name property
     */
    public StringProperty getProjectNameProperty() {
        return this.projectName;
    }

    /**
     * gets the project name property.
     *
     * @return the height property
     */
    public DoubleProperty getHeightProperty() {
        return this.height;
    }

    /**
     * gets the weight property.
     *
     * @return the weight property
     */
    public DoubleProperty getWeightProperty() {
        return this.weight;
    }

    /**
     * gets the length property.
     *
     * @return the length property
     */
    public DoubleProperty getLengthProperty() {
        return this.length;
    }

    /**
     * gets the tagID property.
     *
     * @return the tagID property
     */
    public IntegerProperty getTagIDProperty() {
        return this.tagID;
    }

    /**
     * gets the description property.
     *
     * @return the description property
     */
    public StringProperty getDescriptionProperty() {
        return this.description;
    }

    /**
     * gets the selected animal class.
     *
     * @return the animal class
     */
    public ObjectProperty<AnimalClass> getAnimalClassProperty() {
        return this.animalClass;
    }

    /**
     * gets the animal.
     *
     * @return the animal
     */
    public ObjectProperty<Animal> getAnimalProperty() {
        return this.animal;
    }

    /**
     * gets the project.
     *
     * @return the project
     */
    public ObjectProperty<Project> getProjectProperty() {
        return this.project;
    }

    /**
     * sets the project.
     *
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project.set(project);
        this.projectName.set(project.getName());
    }

    /**
     * refreshed the view project data.
     * @return the project
     */
    public Project refreshProject() {
        if (this.project.get() == null) {
            return null;
        }

        Project currentProject = this.project.get();
        GetSingleProjectRequest request = new GetSingleProjectRequest(this.authSession.getToken(), currentProject.getName(), currentProject.getId());

        return this.serverService.requestSingleProject(request);
    }

    /**
     * clears the animal stats.
     */
    public void clearAnimalStats() {
        this.height.set(0.0);
        this.weight.set(0.0);
        this.length.set(0.0);
        this.tagID.set(0);
        this.description.set("");
    }

    /**
     * sets the animals stats.
     *
     * @param animal the animal to get the stats from
     */
    public void setAnimalStats(Animal animal) {
        this.height.set(animal.getHeight());
        this.weight.set(animal.getWeight());
        this.length.set(animal.getLength());
        this.tagID.set(animal.getTagID());
        this.description.set(animal.getDescription());
    }

    /**
     * gets the animals by type.
     *
     * @param type the type of animal class the animal belongs too
     * @return the list of animals of a specific type
     */
    public List<Animal> getAnimalsByType(AnimalClass type) {
        return this.getProjectProperty().get().getAnimalsByType(type);
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
