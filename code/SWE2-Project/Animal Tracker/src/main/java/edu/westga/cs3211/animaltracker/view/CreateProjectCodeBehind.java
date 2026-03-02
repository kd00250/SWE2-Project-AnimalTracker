package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.viewmodel.AddUserViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

/**
 * The CreateProject CodeBehind.
 *
 * @author mrocker1
 */
public class CreateProjectCodeBehind {

    @FXML
    private Label projectNameLabel;

    @FXML
    private TextField projectNameTextField;

    @FXML
    private Label projectNameWarningLabel;

    @FXML
    private Label projectLocationLabel;

    @FXML
    private TextField projectLocationTextField;

    @FXML
    private Label projectLocationWarningLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label scientistToAddLabel;

    @FXML
    private ListView<User> scientistToAddListView;

    @FXML
    private Button removeScientistToAddButton;

    @FXML
    private Label availableScientistLabel;

    @FXML
    private ListView<User> availableScientistToAddListView;

    @FXML
    private Button addScientistToProjectButton;

    @FXML
    private Button createProjectButton;

    private CreateProjectViewModel vm;

    private void setUpBindings() {
        this.vm = new CreateProjectViewModel();
        this.vm.getProjectNameProperty().bind(this.projectNameTextField.textProperty());
        this.vm.getProjectLocationProperty().bind(this.projectLocationTextField.textProperty());
        this.createProjectButton.disableProperty().bind(this.projectLocationTextField.textProperty().isEmpty().or(this.projectNameTextField.textProperty().isEmpty()));
        this.scientistToAddListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.availableScientistToAddListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.removeScientistToAddButton.disableProperty().bind(this.scientistToAddListView.getSelectionModel().selectedItemProperty().isNull());
        this.addScientistToProjectButton.disableProperty().bind(this.availableScientistToAddListView.getSelectionModel().selectedItemProperty().isNull());
    }

    private void displayErrorPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Project: " + this.vm.getProjectNameProperty().get() + " has been successfully created");
        alert.showAndWait();
    }

    private void displayErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void onBackButtonClick(ActionEvent actionEvent) {
    }

    //TODO Fix Documentation to how event will actually work

    void onAddScientistToProjectClick(ActionEvent actionEvent) {
        this.addScientistToProjectButton.setOnAction(e -> {
            User selectedUser = this.availableScientistToAddListView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                try {
                    this.vm.addScientistToProject(selectedUser);
                } catch (IllegalArgumentException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }

    void onRemoveScientistToAddClick(ActionEvent actionEvent) {
        this.removeScientistToAddButton.setOnAction(e -> {
            User selectedUser = this.scientistToAddListView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                try {
                    this.vm.removeScientistFromProject(selectedUser);
                } catch (IllegalArgumentException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }

    void onCreateProjectClick(ActionEvent actionEvent) {
        String name = this.vm.getProjectNameProperty().get();
        ArrayList<User> scientist = this.vm.getAddedScientist();
        this.vm.createProject(name, scientist);
    }

    @FXML
    void initialize() {
        this.setUpBindings();
    }
}
