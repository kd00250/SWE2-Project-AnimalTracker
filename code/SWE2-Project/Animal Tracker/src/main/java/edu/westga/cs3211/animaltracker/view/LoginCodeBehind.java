package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
        this.viewModel.processLoginRequest();
        if (this.viewModel.isLoginValid()) {
            this.processCorrectLogin();
        }
    }

    private void processCorrectLogin() {
        try {
            LandingPageCodeBehind controller = ViewSwapper.loadPageFromStage(PageInformation.LandingPath, this.mainPane, PageInformation.LandingTitle);
            controller.setAuthenticationSession(this.viewModel.getLoginResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
