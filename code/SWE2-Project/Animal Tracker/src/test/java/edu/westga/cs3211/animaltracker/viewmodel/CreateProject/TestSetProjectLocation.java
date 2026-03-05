package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSetProjectLocation {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testSetProjectLocation() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.getProjectNameProperty().setValue("Baboons");
        vm.setProjectLocation("aaa");

        assertEquals("aaa", vm.getProjectLocationProperty().get());
    }

    @Test
    void testNullProjectLocation() {
        CreateProjectViewModel vm = new CreateProjectViewModel();

        assertThrows(IllegalArgumentException.class, () -> vm.setProjectLocation(null));
    }

    @Test
    void testEmptyProjectLocation() {
        CreateProjectViewModel vm = new CreateProjectViewModel();

        assertThrows(IllegalArgumentException.class, () -> vm.setProjectLocation(""));
    }
}
