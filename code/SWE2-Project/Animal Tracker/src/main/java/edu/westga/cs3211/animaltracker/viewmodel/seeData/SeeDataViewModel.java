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

/**
 * The see data view model class responsible for collecting sighting information from the server.
 */
public class SeeDataViewModel {
    private ServerService serverService;
    private LoginResponse authSession;
    private ObjectProperty<Animal> animalProperty;
    private ListProperty<SightingRowViewModel> sightings;

    /**
     * Sets the server session of the view model to allow server communication and authentication.
     * @param session the current user session
     * @param server the server service to use
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
        this.initializeProperties();
    }

    /**
     * Sets the animal property to use for sighting information.
     * @param animalProperty the animal property
     */
    public void setAnimalProperty(ObjectProperty<Animal> animalProperty) {
        this.animalProperty = animalProperty;
    }

    private void initializeProperties() {
        this.sightings = new SimpleListProperty<>(FXCollections.observableList(this.retrieveSightings()));
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

    /**
     * Gets the server service.
     * @return the server service
     */
    public ServerService getServerService() {
        return this.serverService;
    }

    /**
     * Gets the current session information.
     * @return the session
     */
    public LoginResponse getSession() {
        return this.authSession;
    }

    /**
     * Gets the sightings that were generated from the animal information.
     * @return the list of sightings
     */
    public ListProperty<SightingRowViewModel> sightings() {
        return this.sightings;
    }
}
