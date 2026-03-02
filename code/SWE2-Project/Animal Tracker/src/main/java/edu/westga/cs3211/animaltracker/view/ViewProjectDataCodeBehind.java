package edu.westga.cs3211.animaltracker.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * The View Project Data Code Behind.
 */
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The view project data code behind.
 */
public class ViewProjectDataCodeBehind {

    @FXML
    private ComboBox<?> animalClassComboBox;

    @FXML
    private Label animalLabel;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label heightLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label projectNameLabel;

    @FXML
    private ComboBox<?> subjectComboBox;

    @FXML
    private Button backButton;

    @FXML
    private Button createTagButton;

    @FXML
    private Button getInformationButton;

    @FXML
    private Label tagIDLabel;

    @FXML
    private Button visualizeDataButton;

    @FXML
    private Label weightLabel;

    @FXML
    void onBackButtonClick(ActionEvent event) {

    }

    @FXML
    void onCreateTagButtonClick(ActionEvent event) {

    }

    @FXML
    void onGetInformationButtonClick(ActionEvent event) {

    }


    @FXML
    void visualize(ActionEvent event) {
        //Implementation coming in future sprint :)
        //The map visualization of the data (a nice to have)
    }

    @FXML
    void initialize() {

    }

}

