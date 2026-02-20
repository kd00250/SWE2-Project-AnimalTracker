package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;

public class LandingPageViewModel {
    private LoginResponse authSession;

    public void setSession(LoginResponse session) {
        this.authSession = session;
    }
}
