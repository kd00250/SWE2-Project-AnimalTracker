package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import edu.westga.cs3211.animaltracker.viewmodel.CreateTagViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/**
 * The CreateTagCodeBehind class.
 */
public class CreateTagCodeBehind {

    @FXML
    private TextArea descriptionText;

    @FXML
    private Button generateIdButton;

    @FXML
    private ComboBox<AnimalClass> speciesComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private Label tagId;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private Label errorLabel;

    private final CreateTagViewModel viewModel;

    /**
     * Initializes a CreateTagCodeBehind.
     */
    public CreateTagCodeBehind() {
        this.viewModel = new CreateTagViewModel();
    }

    /**
     * Called when the cancel button is clicked.
     *
     * @param event the event
     */
    @FXML
    public void onCancelClick(ActionEvent event) {
        var confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Cancel");
        confirmationAlert.setHeaderText("Are you sure you want to cancel?");
        confirmationAlert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            confirmationAlert.close();
        } else {
            this.viewModel.clear();
        }
    }

    /**
     * Called when submit button is clicked.
     *
     * @param event the event
     */
    @FXML
    public void onSubmitClick(ActionEvent event) {
        var confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm");
        confirmationAlert.setHeaderText("Are you sure you want to confirm tag information?");
        confirmationAlert.setContentText("Are you sure you want to confirm tag information?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Put Logic for when Submit is confirmed. Such as go to new landing page.
            this.viewModel.makeTag();

            if (animal != null) {
                this.viewModel.clear();

            }
        }
    }

    /**
     * Sets the session for this page.
     * Should be called after loading this view to pass authentication data.
     *
     * @param session the user's session
     * @param server the server to be used
     * @pre session != null && server != null
     * @post session is set and projects are loaded
     */
    public void setSession(LoginResponse session, ServerService server, ViewProjectDataViewModel viewProjectViewModel) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        if (viewProjectViewModel == null) {
            throw new IllegalArgumentException("View model cannot be null");
        }
        this.viewModel.setSession(session, server, viewProjectViewModel);
    }

    /**
     * Called when generateId is clicked.
     *
     * @param event the event
     */
    @FXML
    public void onGenerateIdClick(ActionEvent event) {
        this.viewModel.generateTagId();
    }

    @FXML
    private void initialize() {
        this.speciesComboBox.getItems().addAll(AnimalClass.values());
        this.speciesComboBox.valueProperty().bindBidirectional(this.viewModel.animalClassProperty());

        this.tagId.textProperty().bind(this.viewModel.tagIdProperty());
        this.descriptionText.textProperty().bindBidirectional(this.viewModel.descriptionProperty());
        this.heightTextField.textProperty().bindBidirectional(this.viewModel.heightProperty());
        this.lengthTextField.textProperty().bindBidirectional(this.viewModel.lengthProperty());
        this.weightTextField.textProperty().bindBidirectional(this.viewModel.weightProperty());
        this.errorLabel.textProperty().bind(this.viewModel.errorMessageProperty());
        this.submitButton.disableProperty().bind(this.viewModel.isSubmitInvalid());
    }
}
