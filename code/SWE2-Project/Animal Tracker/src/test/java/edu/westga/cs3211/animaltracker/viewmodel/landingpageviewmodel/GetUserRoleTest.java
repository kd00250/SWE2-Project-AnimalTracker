package edu.westga.cs3211.animaltracker.viewmodel.landingpageviewmodel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.LandingPageViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUserRoleTest {
    @Test
    void testGetValidUserRole() {
        var user = new User("Tim", "1234", Role.SCIENTIST);
        DataStorage.getUsers().clear();
        String token = DataStorage.generateTokenForUser(user);
        var vm = new LandingPageViewModel();
        vm.setSession(new LoginResponse(token, 500), new LocalServer());
        Role userRole = vm.getUserRole();
        assertEquals(user.role(), userRole);
    }

    @Test
    void testGetInvalidUserRole() {
        var vm = new LandingPageViewModel();
        vm.setSession(new LoginResponse("1234", 500), new LocalServer());
        Role userRole = vm.getUserRole();
        assertNull(vm.getUserRole());
    }
}
