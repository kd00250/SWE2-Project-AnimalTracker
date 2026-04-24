package edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewmodel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewModel;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestProperties {
    private SeeDataViewModel seeDataViewModel;
    private ServerService server;
    private Animal animal;
    @BeforeEach
    void setUp() {
        var vm = new SeeDataViewModel();
        var animal0 = new Animal(AnimalClass.MAMMAL, 10, 10, 10, 10, "Bird thing");
        var server = new FakeServerService();
        this.seeDataViewModel = vm;
        this.server = server;
        this.animal = animal0;
    }

    @Test
    void testInitializesWithNoSightings() {
        this.seeDataViewModel.setAnimalProperty(new SimpleObjectProperty<>(this.animal));
        this.seeDataViewModel.setSession(new LoginResponse("1234", 1234), this.server);
        assertNotNull(this.seeDataViewModel.sightings());
        assertEquals(0, this.seeDataViewModel.sightings().size());
    }

    @Test
    void testMultipleSightingShouldLoad() {
        var sighting0 = new Sighting(10,
                "Florida", 10.6, 60.6,
                LocalDateTime.of(2024, 4, 20,10, 40),
                "Notes", "Bob");

        this.server.addSighting(new AddSightingRequest("Token", sighting0));
        this.seeDataViewModel.setAnimalProperty(new SimpleObjectProperty<>(this.animal));
        this.seeDataViewModel.setSession(new LoginResponse("1234", 1234), this.server);
        assertEquals(1, this.seeDataViewModel.sightings().size());

        assertEquals("Florida", this.seeDataViewModel.sightings().get(0).locationProperty().getValue());
        assertEquals(10.6, this.seeDataViewModel.sightings().get(0).latitudeProperty().getValue());
        assertEquals(60.6, this.seeDataViewModel.sightings().get(0).longitudeProperty().getValue());
        assertEquals("Bob", this.seeDataViewModel.sightings().get(0).usernameProperty().getValue());
    }

    @Test
    void testGetServerSessions() {
        this.seeDataViewModel.setAnimalProperty(new SimpleObjectProperty<>(this.animal));

        var response = new LoginResponse("1234", 1234);
        var server = this.server;
        this.seeDataViewModel.setSession(response, server);
        assertEquals(server, this.seeDataViewModel.getServerService());
        assertEquals(response, this.seeDataViewModel.getSession());
    }





}
