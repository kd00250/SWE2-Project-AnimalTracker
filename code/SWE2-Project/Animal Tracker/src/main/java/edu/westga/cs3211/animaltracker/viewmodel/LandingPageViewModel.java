package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;

public class LandingPageViewModel {
    private LoginResponse authSession;
    private ServerService serverService;

    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
    }

    public LoginResponse getSession() {
        return this.authSession;
    }

    public Role getUserRole() {
        var request = new UserDataRequest(this.authSession.getToken());
        return this.serverService.requestUserRole(request);
    }


}
