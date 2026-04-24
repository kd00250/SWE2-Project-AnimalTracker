package edu.westga.cs3211.animaltracker.viewmodel.seeData;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.GetSightingRequest;
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

    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
        this.initializeProperties();
    }
    public void setAnimalProperty(ObjectProperty<Animal> animalProperty) {
        this.animalProperty = animalProperty;
    }
    private void initializeProperties() {
        this.sightings = new SimpleListProperty<>(FXCollections.observableList(retrieveSightings()));
    }

    private List<SightingRowViewModel> retrieveSightings() {
        var request = new GetSightingRequest(this.authSession.getToken(), animalProperty.get().getTagID());
        var sightings = this.serverService.getSightings(request);
        var sightingViewModels = new ArrayList<SightingRowViewModel>();
        for (Sighting sighting : sightings) {
            sightingViewModels.add(new SightingRowViewModel(sighting));
        }
        return sightingViewModels;
    }


    public ServerService getServerService() {
        return serverService;
    }

    public LoginResponse getSession() {
        return authSession;
    }


    public ListProperty<SightingRowViewModel> sightings() {
        return this.sightings;
    }
}
