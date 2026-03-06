package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetProject {

    @Test
    void testValidProject() {
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        Project project = DataStorage.getProjects().get(1);
        vm.setProject(project);

        assertEquals(project, vm.getProjectProperty().get());
        assertEquals(project.getName(), vm.getProjectNameProperty().get());
    }
}
