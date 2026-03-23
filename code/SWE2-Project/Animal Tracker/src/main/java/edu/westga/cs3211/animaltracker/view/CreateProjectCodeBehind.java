package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;


/**
 * The CreateProject CodeBehind.
 *
 * @author mrocker1
 */
public class CreateProjectCodeBehind {

    @FXML
    private TextField projectNameTextField;

    @FXML
    private Label projectNameWarningLabel;

    @FXML
    private TextField projectLocationTextField;

    @FXML
    private Label projectLocationWarningLabel;

    @FXML
    private Button backButton;

    @FXML
    private ListView<User> scientistToAddListView;

    @FXML
    private Button removeScientistToAddButton;

    @FXML
    private ListView<User> availableScientistToAddListView;

    @FXML
    private Button addScientistToProjectButton;

    @FXML
    private Button createProjectButton;

    private CreateProjectViewModel vm;

    void setUpBindings() {
        this.vm = new CreateProjectViewModel();
        this.vm.getProjectNameProperty().bind(this.projectNameTextField.textProperty());
        this.vm.getProjectLocationProperty().bind(this.projectLocationTextField.textProperty());
        this.createProjectButton.disableProperty().bind(this.projectLocationTextField.textProperty().isEmpty().or(this.projectNameTextField.textProperty().isEmpty()));
        this.availableScientistToAddListView.getItems().addAll(this.vm.getAvailableScientists());
        this.scientistToAddListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.availableScientistToAddListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.removeScientistToAddButton.disableProperty().bind(this.scientistToAddListView.getSelectionModel().selectedItemProperty().isNull());
        this.addScientistToProjectButton.disableProperty().bind(this.availableScientistToAddListView.getSelectionModel().selectedItemProperty().isNull());
    }

    private void setUpListeners() {
        this.projectNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isBlank()) {
                this.projectNameWarningLabel.setText("Invalid: Project Name cannot be blank");
                this.projectNameWarningLabel.setVisible(true);
            } else {
                this.projectNameWarningLabel.setVisible(false);
            }
        });

        this.projectLocationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isBlank()) {
                this.projectLocationWarningLabel.setText("Invalid: Project Location cannot be blank");
                this.projectLocationWarningLabel.setVisible(true);
            } else {
                this.projectLocationWarningLabel.setVisible(false);
            }
        });
    }

    private void displaySuccessPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Project: " + this.vm.getProjectNameProperty().get() + " has been successfully created");
        alert.showAndWait();
    }

    private void displayErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
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

    @FXML
    void onBackButtonClick(ActionEvent actionEvent) {
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

    //Fix Documentation to how event will actually work

    @FXML
    void onAddScientistToProjectClick(ActionEvent actionEvent) {
        User selectedUser = this.availableScientistToAddListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                this.vm.addScientistToProject(selectedUser);
                this.scientistToAddListView.getItems().add(selectedUser);
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void onRemoveScientistToAddClick(ActionEvent actionEvent) {
        User selectedUser = this.scientistToAddListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                this.vm.removeScientistFromProject(selectedUser);
                this.scientistToAddListView.getItems().remove(selectedUser);
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void onCreateProjectClick(ActionEvent actionEvent) {
        String name = this.vm.getProjectNameProperty().get();
        ArrayList<User> scientist = this.vm.getAddedScientist();
        this.vm.createProject(name, scientist);
        this.displaySuccessPopup();
    }

    @FXML
    void initialize() {
        this.setUpBindings();
        this.setUpListeners();
    }
}
