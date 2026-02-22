package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.LandingPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LandingPageCodeBehind {
    private LandingPageViewModel landingViewModel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void initialize() {
        this.landingViewModel = new LandingPageViewModel();
    }

    @FXML
    void onCreateProjectClick(ActionEvent event) {
        try {
            CreateProjectCodeBehind controller = ViewSwapper.loadPageFromStage(PageInformation.CreateProjectPath, this.mainPane, PageInformation.CreateProjectTitle);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onViewProjectClick(ActionEvent event) {
        try {
            ViewProjectDataCodeBehind controller = ViewSwapper.loadPageFromStage(PageInformation.ViewProjectPath, this.mainPane, PageInformation.ViewProjectTitle);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthenticationSession(LoginResponse session) {
        this.landingViewModel.setSession(session);
    }
}
