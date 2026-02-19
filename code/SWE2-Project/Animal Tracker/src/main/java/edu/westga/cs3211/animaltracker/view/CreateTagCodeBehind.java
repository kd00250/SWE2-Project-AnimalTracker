package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.viewmodel.CreateTagViewModel;
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

    private final CreateTagViewModel viewModel;

    public CreateTagCodeBehind(){
        this.viewModel = new CreateTagViewModel();
    }

    /**
     * Called when the cancel button is clicked.
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

    @FXML
    public void onSubmitClick(ActionEvent event) {
        var confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm");
        confirmationAlert.setHeaderText("Are you sure you want to confirm tag information?");
        confirmationAlert.setContentText("Are you sure you want to confirm tag information?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Put Logic for when Submit is confirmed. Such as go to new landing page.
        }
    }

    @FXML
    public void onGenerateIdClick(ActionEvent event){
        this.viewModel.generateTagId();
    }

    @FXML
    private void initialize() {
        this.speciesComboBox.getItems().addAll(AnimalClass.values());
        this.speciesComboBox.valueProperty().bindBidirectional(this.viewModel.animalClassProperty());

        this.tagId.textProperty().bind(this.viewModel.tagIdProperty());
        this.descriptionText.textProperty().bindBidirectional(this.viewModel.descriptionProperty());

        this.submitButton.disableProperty().bind(this.viewModel.canSubmit());
    }
}
