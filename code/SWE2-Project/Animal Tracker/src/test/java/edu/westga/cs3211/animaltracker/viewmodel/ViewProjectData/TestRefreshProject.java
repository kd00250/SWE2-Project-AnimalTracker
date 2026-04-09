package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestRefreshProject {
    @Test
    void testRefreshReturnsNullWithNoProjectProject() {
        var vm = new ViewProjectDataViewModel();
        vm.getProjectProperty().set(null);
        assertNull(vm.refreshProject());
    }

    @Test
    void testRefreshReturnsSameProjectStoredInViewModel() {
        var vm = new ViewProjectDataViewModel();
        DataStorage.reset();
        var user0 = new User("123", "141414", Role.SCIENTIST);
        DataStorage.getUsers().add(user0);
        var token = DataStorage.generateTokenForUser(user0);
        vm.setSession(new LoginResponse(token, 1000), new LocalServer());
        var project0 = new Project(List.of(), "name", List.of());
        DataStorage.addProject(project0);
        vm.getProjectProperty().set(project0);
        assertEquals(project0, vm.refreshProject());
    }
}
