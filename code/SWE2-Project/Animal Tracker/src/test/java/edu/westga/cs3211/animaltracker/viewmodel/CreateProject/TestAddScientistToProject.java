package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestAddScientistToProject {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testAddScientistToProject() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.addScientistToProject(vm.getAvailableScientists().getFirst());

        assertEquals(1, vm.getAddedScientist().size());
        assertEquals("Bob", vm.getAddedScientist().getFirst().username());
        assertEquals("1234", vm.getAddedScientist().getFirst().password());
        assertEquals(Role.SCIENTIST, vm.getAddedScientist().getFirst().role());
    }

    @Test
    void testAddDupScientist() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.addScientistToProject(vm.getAvailableScientists().getFirst());
        assertThrows(IllegalArgumentException.class, () -> vm.addScientistToProject(vm.getAvailableScientists().getFirst()));
    }
}
