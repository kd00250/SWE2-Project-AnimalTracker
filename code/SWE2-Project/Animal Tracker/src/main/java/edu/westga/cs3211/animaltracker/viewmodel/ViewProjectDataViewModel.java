package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import javafx.beans.property.*;

import java.util.List;

/**
 * the view project data view model.
 */
public class ViewProjectDataViewModel {
    private LoginResponse authSession;
    private ServerService serverService;
    private ObjectProperty<AnimalClass> animalClass;
    private ObjectProperty<Animal> animal;
    private ObjectProperty<Project> project;
    private StringProperty projectName;
    private DoubleProperty height;
    private DoubleProperty weight;
    private DoubleProperty length;
    private IntegerProperty tagID;
    private StringProperty description;

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
