package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import edu.westga.cs3211.animaltracker.viewmodel.AddUserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * the add user code behind class.
 */
public class AddUserCodeBehind {

    @FXML
    private Button addUserButton;

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
        } catch (IllegalArgumentException exception) {
            this.displayErrorPopup(exception.getMessage());
        }
    }

    private void setUpControls() {
        this.roleComboBox.getItems().addAll(Role.values());
        this.roleComboBox.setValue(Role.values()[0]);
        this.usernameTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.startsWith(" ")) {
                this.usernameTextField.setText(oldVal);
            }
            this.addUserButton.setDisable(newVal.isBlank() || this.passwordTextField.getText().isBlank());
        });

        this.passwordTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.startsWith(" ")) {
                this.passwordTextField.setText(oldVal);
            }
            this.addUserButton.setDisable(newVal.isBlank() || this.usernameTextField.getText().isBlank());
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

    @FXML
    void initialize() {
        this.setUpBindings();
        this.setUpControls();
    }

}

