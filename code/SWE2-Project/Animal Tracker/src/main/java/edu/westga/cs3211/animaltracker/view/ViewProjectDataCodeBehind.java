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
public class ViewProjectDataCodeBehind {

    @FXML
    private Label animalLabel;

    @FXML
    private ComboBox<?> animalSpeciesComboBox;

    @FXML
    private ComboBox<?> animalTypeComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label heightLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label projectNameLabel;

    @FXML
    private Label tagIDLabel;

    @FXML
    private Button visualizeDataButton;

    @FXML
    private Label weightLabel;

    @FXML
    void visualize(ActionEvent event) {

    }

}
