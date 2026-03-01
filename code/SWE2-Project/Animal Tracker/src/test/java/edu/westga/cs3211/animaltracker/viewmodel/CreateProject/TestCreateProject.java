package edu.westga.cs3211.animaltracker.viewmodel.CreateProject;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCreateProject {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testCreateProjectNoScientists() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        vm.getProjectNameProperty().setValue("Baboons");
        vm.getProjectLocationProperty().setValue("");
        vm.createProject(vm.getProjectNameProperty().get(), vm.getAddedScientist());

        assertEquals("Baboons", DataStorage.getProjects().get(2).getName());
        assertEquals(0, DataStorage.getProjects().get(2).getUsers().size());
    }

    @Test
    void testCreateProjectWithScientists() {
        CreateProjectViewModel vm = new CreateProjectViewModel();
        var user = new User("John Doe", "123456789", Role.SCIENTIST);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        vm.createProject("Pokemon", users);
    }
}
