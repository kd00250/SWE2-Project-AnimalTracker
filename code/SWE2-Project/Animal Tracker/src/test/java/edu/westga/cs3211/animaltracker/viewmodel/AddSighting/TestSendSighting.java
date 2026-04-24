package edu.westga.cs3211.animaltracker.viewmodel.AddSighting;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidResponseException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.AddSightingViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestSendSighting {
    private String token;
    private int animalID;

    @BeforeEach
    void setUp() {
        DataStorage.reset();
        Animal animal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 1234, "Big bird with Wings");
        this.animalID = animal.getId();
        Project project = new Project(new ArrayList<User>(), "Test Project", new ArrayList<Animal>());
        project.addAnimal(animal);
        DataStorage.addProject(project);
        var user0 = new User("Bob", "0987", Role.CONTRIBUTOR);
        DataStorage.getUsers().add(user0);
        this.token = DataStorage.generateTokenForUser(user0);
    }

    @Test
    void TestAddValidSighting() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(0);
        vm.minuteProperty().setValue(0);
        vm.noteProperty().setValue("Saw it flying with a flock.");
        System.out.println(DataStorage.getAnimalById(this.animalID));
        var result = vm.sendSighting();

        assertTrue(result);
        assertEquals(this.token, vm.getSession().getToken());
        assertEquals(LocalServer.class, vm.getServerService().getClass());
    }

    @Test
    void TestAddValidSightingWithTimeInDoubleDigits() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");
        var result = vm.sendSighting();

        assertTrue(result);
    }

    @Test
    void TestAnimalIDThatDoesntExistInServer() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(12));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(InvalidResponseException.class, vm::sendSighting);
    }

    @Test
    void TestNegativeAnimalID() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(-1));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestEmptyLocation() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestWhitespaceLocation() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("   ");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestBelowValidLatitude() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("-90.1");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestAboveValidLatitude() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("90.1");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestBelowValidLongitude() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("-180.1");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestAboveValidLongitude() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("180.1");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestDateInFuture() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15");
        vm.dateProperty().setValue(LocalDate.of(3000, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestEmptyNotes() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }

    @Test
    void TestWhitespaceNotes() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.setSession(new LoginResponse(this.token, 1000), new LocalServer());
        vm.animalIDProperty().setValue(Integer.toString(this.animalID));
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(10);
        vm.noteProperty().setValue("   ");

        assertThrows(IllegalArgumentException.class, vm::sendSighting);
    }
}