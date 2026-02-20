package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;
import edu.westga.cs3211.animaltracker.viewmodel.LandingPageViewModel;
import javafx.fxml.FXML;

public class LandingPageCodeBehind {
    private LandingPageViewModel landingViewModel;

    @FXML
    void initialize() {
        this.landingViewModel = new LandingPageViewModel();
    }

    public void setAuthenticationSession(LoginResponse session) {
        this.landingViewModel.setSession(session);
    }
}
