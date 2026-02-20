package edu.westga.cs3211.animaltracker.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * The CreateTagCodeBehind class.
 */
public class CreateTagCodeBehind {

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
        }
    }
}
