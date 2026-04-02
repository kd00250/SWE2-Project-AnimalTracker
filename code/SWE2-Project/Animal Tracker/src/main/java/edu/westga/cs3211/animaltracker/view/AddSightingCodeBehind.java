package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

import java.io.IOException;

/**
 * Code-behind (controller) for addSighting.fxml.
 */
public class AddSightingCodeBehind {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> animalComboBox;
    @FXML
    private ComboBox<?> locationComboBox;

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

    @FXML
    private void initialize() {
        this.hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12));
        this.minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            LandingPageCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.LANDING_PATH,
                    this.backButton,
                    PageInformation.LANDING_TITLE
            );

//            controller.setSession(
//                    this.vm.getSession(),
//                    this.vm.getServerService()
//            );

        } catch (IOException e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }

    @FXML
    public void onSaveSightingClick(ActionEvent event) {
        // Intentionally left blank.
    }

    @FXML
    public void onClearClick(ActionEvent event) {
        // Intentionally left blank.
    }
}