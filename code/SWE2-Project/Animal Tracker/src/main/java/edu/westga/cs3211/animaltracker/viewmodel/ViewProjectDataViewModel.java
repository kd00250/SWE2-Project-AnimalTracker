package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * the view project data view model.
 */
public class ViewProjectDataViewModel {
    private LoginResponse authSession;
    private ServerService serverService;
    private ObjectProperty<AnimalClass> animalClass;
    private ObjectProperty<Animal> animal;
    private ObjectProperty<Project> project;

    /**
     * creates a new instance of view project view model.
     */
    public ViewProjectDataViewModel() {
        this.animal = new SimpleObjectProperty<>();
        this.animalClass = new SimpleObjectProperty<>();
        this.project = new SimpleObjectProperty<>();
    }

    //public get
}
