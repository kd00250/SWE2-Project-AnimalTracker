package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.AddSightingViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Code-behind (controller) for addSighting.fxml.
 */
public class AddSightingCodeBehind {

    @FXML
    private Button backButton;

    @FXML
    private TextField animalTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField latitudeTextField;
    @FXML
    private TextField longitudeTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private TextArea noteTextArea;

    @FXML
    private Label warningLabel;

    @FXML
    private Button saveSightingButton;
    @FXML
    private Button clearButton;

    private AddSightingViewModel vm;

    @FXML
    private void initialize() {
        this.hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
        this.minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        this.vm = new AddSightingViewModel();
        this.bindAllProperties();
    }

    private void bindAllProperties() {
        this.animalTextField.textProperty().bind(this.vm.animalIDProperty());
        this.locationTextField.textProperty().bind(this.vm.locationProperty());
        this.latitudeTextField.textProperty().bind(this.vm.latitudeProperty());
        this.longitudeTextField.textProperty().bind(this.vm.longitudeProperty());
        this.datePicker.accessibleTextProperty().bind(this.vm.dateProperty());
        this.hourSpinner.accessibleTextProperty().bind(this.vm.hourProperty());
        this.minuteSpinner.accessibleTextProperty().bind(this.vm.minuteProperty());
        this.noteTextArea.textProperty().bind(this.vm.noteProperty());
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

    /**
     * Shows an error alert to the user.
     *
     * @param message the error message
     * @pre message != null
     * @post alert is displayed to user
     */
    private void displayErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void onSaveSightingClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sighting Saved");
        alert.setHeaderText(null);
        alert.setContentText("Sighting Saved!");
        alert.showAndWait();
    }

    @FXML
    void onClearClick(ActionEvent event) {
        this.vm.clearAllFields();
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
}