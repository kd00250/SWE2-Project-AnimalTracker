package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.AddUserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * the add user code behind class.
 */
public class AddUserCodeBehind {

    @FXML
    private Button addUserButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    private AnchorPane pane;

    private AddUserViewModel vm;

    @FXML
    void addUser(ActionEvent event) {
        try {
            this.vm.createNewUser();
            this.displaySuccessPopup();
            this.usernameTextField.clear();
            this.passwordTextField.clear();
        } catch (IllegalArgumentException exception) {
            this.displayErrorPopup(exception.getMessage());
        }
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            LandingPageCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.LANDING_PATH,
                    this.backButton,
                    PageInformation.LANDING_TITLE
            );

            controller.setSession(
                    this.vm.getSession(),
                    this.vm.getServerService()
            );

        } catch (IOException e) {
            this.displayErrorPopup("Failed to navigate back: " + e.getMessage());
        }
    }

    private void setUpControls() {
        this.roleComboBox.getItems().addAll(Role.values());
        this.roleComboBox.setValue(Role.values()[0]);
        this.addUserButton.disableProperty().bind(this.usernameTextField.textProperty().isEmpty().or(this.passwordTextField.textProperty().isEmpty()));
        this.usernameTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.startsWith(" ")) {
                this.usernameTextField.setText(oldVal);
            }
        });

        this.passwordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.startsWith(" ")) {
                this.passwordTextField.setText(oldVal);
            }
        });

    }

    void setSession(LoginResponse session, ServerService server) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        this.vm.setSession(session, server);
    }

    private void setUpBindings() {
        this.vm = new AddUserViewModel();
        this.vm.getUsername().bind(this.usernameTextField.textProperty());
        this.vm.getPassword().bind(this.passwordTextField.textProperty());
        this.vm.getRole().bind(this.roleComboBox.valueProperty());
    }

    private void displayErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displaySuccessPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Username: " + this.vm.getUsername().get() + " Password: " + this.vm.getPassword().get() + " and Role: " + this.vm.getRole().get() + " user has been successfully created");
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        this.setUpBindings();
        this.setUpControls();
    }

}

