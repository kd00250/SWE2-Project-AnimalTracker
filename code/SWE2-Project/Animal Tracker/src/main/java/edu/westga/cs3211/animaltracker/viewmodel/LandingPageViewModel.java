package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;

/**
 * The landing page view model class.
 */
public class LandingPageViewModel {
    private LoginResponse authSession;
    private ServerService serverService;

    /**
     * Sets the session for this view model.
     * @param session the users session
     * @param server the server
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
    }

    /**
     * Gets the session information.
     * @return the session
     */
    public LoginResponse getSession() {
        return this.authSession;
    }

    /**
     * Gets the Server Service Information.
     * @return the ServerService
     */
    public ServerService getServerService() {
        return this.serverService;
    }

    /**
     * Gets the role for the logged-in user.
     * @return the users role
     */
    public Role getUserRole() {
        var request = new UserDataRequest(this.authSession.getToken());
        return this.serverService.requestUserRole(request);
    }

}
