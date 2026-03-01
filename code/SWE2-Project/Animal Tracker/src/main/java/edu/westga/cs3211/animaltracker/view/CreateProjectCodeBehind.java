package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.viewmodel.AddUserViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    //TODO Check to make sure that it will be Scientist/Correct Object
    @FXML
    private ListView<String> scientistToAddListView;

    @FXML
    private Button removeScientistToAddButton;

    @FXML
    private Label availableScientistLabel;

    //TODO Check to make sure that it will be Scientist/Correct Object
    @FXML
    private ListView<String> availableScientistToAddListView;

    @FXML
    private Button addScientistToProjectButton;

    @FXML
    private Button createProjectButton;

    private CreateProjectViewModel vm;

    private void setUpBindings() {
        this.vm = new CreateProjectViewModel();

    }

    private void displayErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        this.setUpBindings();
        //this.setUpControls();
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Goes back to Landing Page.
     *
     * @param actionEvent the event
     */
    public void onBackButtonClick(ActionEvent actionEvent) {
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Handles when the Add Scientist button is clicked.
     *
     * @param actionEvent the event
     */
    public void onAddScientistToProjectClick(ActionEvent actionEvent) {
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Handles when the Remove Scientist button is clicked.
     *
     * @param actionEvent the event
     */
    public void onRemoveScientistToAddClick(ActionEvent actionEvent) {
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Handles when the Create Project button is clicked.
     *
     * @param actionEvent the event
     */
    public void onCreateProjectClick(ActionEvent actionEvent) {
    }
}
