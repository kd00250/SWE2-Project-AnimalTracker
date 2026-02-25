package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.LandingPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LandingPageCodeBehind {
    private LandingPageViewModel landingViewModel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button createProjectButton;

    @FXML
    private Button viewProjectButton;

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
    private void disableBasedOnRole(Role role) {
        if (role == Role.CONTRIBUTOR || role == Role.GUEST || role == Role.ADMIN) {
            this.createProjectButton.setDisable(true);
            this.viewProjectButton.setDisable(true);
        }
    }
    public void setSession(LoginResponse session, ServerService server) {
        this.landingViewModel.setSession(session, server);
        var usersRole = this.landingViewModel.getUserRole();
        this.disableBasedOnRole(usersRole);
    }
}
