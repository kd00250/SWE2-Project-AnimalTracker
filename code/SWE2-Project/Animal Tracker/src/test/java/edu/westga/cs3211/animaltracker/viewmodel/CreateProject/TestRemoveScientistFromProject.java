package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRemoveScientistFromProject {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testRemoveScientistFromProject() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.addScientistToProject(vm.getAvailableScientists().getFirst());
        vm.removeScientistFromProject(vm.getAvailableScientists().getFirst());

        assertEquals(0, vm.getAddedScientist().size());
    }
}
