package edu.westga.cs3211.animaltracker.view.codebehind;
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
 * The view project data code behind
 */
public class ViewProjectDataCodeBehind {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Label tagIDLabel;

    @FXML
    private Button visualizeDataButton;

    @FXML
    private Label weightLabel;

    @FXML
    void visualize(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}

