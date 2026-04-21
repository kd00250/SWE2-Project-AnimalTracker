package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.LandingPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The landing page code behind.
 */
public class LandingPageCodeBehind {
    private LandingPageViewModel landingViewModel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button addUserButton;

    @FXML
    private Button viewProjectButton;

    @FXML
    void initialize() {
        this.landingViewModel = new LandingPageViewModel();

    }

    @FXML
    void onAddUserButtonClick(ActionEvent event) {

        try {
            AddUserCodeBehind controller = ViewSwapper.loadPageFromStage(PageInformation.ADD_USER_PATH, this.mainPane, PageInformation.ADD_USER_TITLE);

            controller.setSession(
                    this.landingViewModel.getSession(),
                    this.landingViewModel.getServerService()
            );
        } catch (IOException e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        try {
            ViewSwapper.loadPageFromStage(PageInformation.LOGIN_PATH, this.mainPane, PageInformation.LOGIN_TITLE);
        } catch (IOException e) {
            throw new RuntimeException("Unable to logout");
        }
    }

    @FXML
    void onViewProjectClick(ActionEvent event) {
        try {

            SelectProjectCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.SELECT_PROJECT_PATH,
                    this.mainPane,
                    PageInformation.SELECT_PROJECT_TITLE
            );

            controller.setSession(
                    this.landingViewModel.getSession(),
                    this.landingViewModel.getServerService()
            );

            controller.refreshProjects();
        } catch (IOException e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }

    /**
     * Called when the add sighting button is clicked.
     * @param actionEvent the click event
     */
    public void onAddSightingClick(ActionEvent actionEvent) {
        try {

            AddSightingCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.ADD_SIGHTING_PATH,
                    this.mainPane,
                    PageInformation.ADD_SIGHTING_TITLE
            );

            controller.setSession(
                    this.landingViewModel.getSession(),
                    this.landingViewModel.getServerService()
            );

        } catch (IOException e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }

    private void disableBasedOnRole(Role role) {
        if (role == Role.CONTRIBUTOR || role == Role.GUEST || role == Role.ADMIN) {
            this.viewProjectButton.setDisable(true);
        }
        if (!(role == Role.ADMIN)) {
            this.addUserButton.setDisable(true);
        }
    }

    /**
     * Sets the session for this page.
     * @param session the users session
     * @param server the server to be used
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.landingViewModel.setSession(session, server);
        var usersRole = this.landingViewModel.getUserRole();
        this.disableBasedOnRole(usersRole);
    }
}
