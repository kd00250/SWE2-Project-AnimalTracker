package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetProject {

    @BeforeEach
    void setUp() {
        DataStorage.getUsers().clear();
        DataStorage.getProjects().clear();
        DataStorage.getUsernameMap().clear();
    }

    @Test
    void testValidProject() {
        var newProject = new Project(new ArrayList<User>(), "test", new ArrayList<Animal>(), 1234);
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        Project project = DataStorage.getProjects().get(1);
        vm.setProject(project);

        assertEquals(project, vm.getProjectProperty().get());
        assertEquals(project.getName(), vm.getProjectNameProperty().get());
    }
}
