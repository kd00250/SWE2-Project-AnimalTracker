package edu.westga.cs3211.animaltracker.view;


import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The login code behind class.
 */
public class LoginCodeBehind {

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

    private LoginViewModel viewModel;

    @FXML
    void initialize() {
        this.viewModel = new LoginViewModel();
        this.bindProperties();
    }

    private void bindProperties() {
        this.viewModel.passwordProperty().bind(this.passwordTextField.textProperty());
        this.viewModel.usernameProperty().bind(this.usernameTextField.textProperty());
    }

    @FXML
    void onLoginClick(ActionEvent event) {
        try {
            this.viewModel.processLoginRequest();
            if (this.viewModel.isLoginValid()) {
                this.processCorrectLogin();
            } else {
                this.displayLoginError("Invalid username or password");
            }
        } catch (Exception e) {
            this.displayLoginError("Missing credentials");
        }

    }
    private void displayLoginError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void processCorrectLogin() {
        try {
            LandingPageCodeBehind controller = ViewSwapper.loadPageFromStage(PageInformation.LANDING_PATH, this.mainPane, PageInformation.LANDING_TITLE);
            controller.setSession(this.viewModel.getLoginResponse(), this.viewModel.getServerService());
        } catch (IOException e) {
            System.err.println("Error processing login request");
        }
    }

}
