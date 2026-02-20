package edu.westga.cs3211.animaltracker.view.codebehind;

import edu.westga.cs3211.animaltracker.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginCodeBehind {

    @FXML
    private Button loginButton;

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
        return; //TODO implement login
    }


}
