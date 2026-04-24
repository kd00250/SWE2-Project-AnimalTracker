package edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewmodel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewModel;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestSetSession {
    private SeeDataViewModel seeDataViewModel;
    private ServerService server;
    private Animal animal;
    @BeforeEach
    void setUp() {
        var vm = new SeeDataViewModel();
        var animal0 = new Animal(AnimalClass.MAMMAL, 10, 10, 10, 10, "Bird thing");

        this.seeDataViewModel = vm;

        this.animal = animal0;
    }
    @Test
    void testWhenInvalidRequestState() {
        var server = new FakeServerService();
        server.setShouldThrow(true);
        this.server = server;
        var response = new LoginResponse("1234", 1234);
        this.seeDataViewModel.setAnimalProperty(new SimpleObjectProperty<>(this.animal));
        assertThrows(IllegalArgumentException.class, () -> this.seeDataViewModel.setSession(response, server));
    }

}
