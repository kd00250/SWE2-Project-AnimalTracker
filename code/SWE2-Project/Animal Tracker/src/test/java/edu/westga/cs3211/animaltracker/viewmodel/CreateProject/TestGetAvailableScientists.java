package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetAvailableScientists {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testGetAvailableScientist() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        User user = new User("Bill", "ppp", Role.ADMIN);
        DataStorage.getUsers().add(user);

        assertEquals(1, vm.getAvailableScientists().size());
        assertEquals("Bob", vm.getAvailableScientists().getFirst().username());
        assertEquals("1234", vm.getAvailableScientists().getFirst().password());
        assertEquals(Role.SCIENTIST, vm.getAvailableScientists().getFirst().role());
    }
}
