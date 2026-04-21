package edu.westga.cs3211.animaltracker.viewmodel.seeData;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeeDataViewModel {
    private ServerService serverService;
    private LoginResponse authSession;
    private ObjectProperty<Animal> animalProperty;
    private ListProperty<SightingRowViewModel> sightings;

    public SeeDataViewModel(ObjectProperty<Animal> animal) {
        this.animalProperty = animal;
        this.initializeProperties();
    }
    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
    }

    private void initializeProperties() {
        var items = this.initializeWithDefaultTest();
        var temp = new ArrayList<SightingRowViewModel>();
        for (Sighting sighting : items) {
            temp.add(new SightingRowViewModel(sighting));
        }
        this.sightings = new SimpleListProperty<>(FXCollections.observableList(temp));
    }

    private List<Sighting> retrieveSightings() {
        var sightings = this.serverService.
    }
    //TODO REMOVE
    private ArrayList<Sighting> initializeWithDefaultTest() {
        var animal = new Animal(AnimalClass.MAMMAL, 10, 20, 50, 1, "Tall Mammal");
        var sighting0 = new Sighting(animal, "NYC", 10.5, 20.6, LocalDateTime.now(), "Bird that is really cool and coooool");
        var sighting1 = new Sighting(animal, "FLORIDA", 50.6, 60.5, LocalDateTime.now(), "Good notes for a mammal in florda");
        var list = new ArrayList<Sighting>();
        list.add(sighting0);
        list.add(sighting1);
        return list;
    }

    public ListProperty<SightingRowViewModel> sightings() {
        return this.sightings;
    }
}
