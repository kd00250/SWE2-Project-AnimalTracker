package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * The view project data code behind.
 */
public class ViewProjectDataCodeBehind {

    @FXML
    private ComboBox<AnimalClass> animalClassComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label heightLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label projectNameLabel;

    @FXML
    private ComboBox<Animal> subjectComboBox;

    @FXML
    private Button backButton;

    @FXML
    private Button createTagButton;

    @FXML
    private Label tagIDLabel;

    @FXML
    private Button visualizeDataButton;

    @FXML
    private Label weightLabel;

    private ViewProjectDataViewModel vm;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            SelectProjectCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.SELECT_PROJECT_PATH,
                    this.backButton,
                    PageInformation.SELECT_PROJECT_TITLE
            );

            controller.setSession(
                    this.vm.getSession(),
                    this.vm.getServerService()
            );

            controller.refreshProjects();
        } catch (IOException e) {
            this.displayErrorPopup("Failed to navigate back: " + e.getMessage());
        }
    }

    @FXML
    void onCreateTagButtonClick(ActionEvent event) {
        //Jake pass in the create tag page
        try {
            CreateTagCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.CREATE_TAG_PATH,
                    this.createTagButton,
                    PageInformation.CREATE_TAG_TITLE
            );

            controller.setSession(
                    this.vm.getSession(),
                    this.vm.getServerService(),
                    this.vm
            );

        } catch (IOException e) {
            this.displayErrorPopup("Failed to navigate back: " + e.getMessage());
        }
    }

    @FXML
    void onAnimalClassChange(ActionEvent event) {
        this.subjectComboBox.getItems().clear();
        this.vm.clearAnimalStats();
        this.subjectComboBox.getItems().addAll(this.vm.getAnimalsByType(this.vm.getAnimalClassProperty().get()));
    }

    @FXML
    void onSubjectChange(ActionEvent event) {
        if (!this.subjectComboBox.getItems().isEmpty()) {
            this.vm.setAnimalStats(this.subjectComboBox.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void visualize(ActionEvent event) {
        //Implementation coming in future sprint :)
        //The map visualization of the data (a nice to have (said in the sprint planning :) )
    }

    private void displayErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
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

    void setProject(Project project) {
        this.vm = new ViewProjectDataViewModel();
        this.vm.setProject(project);
        this.setUpBindings();
    }

    private void setUpBindings() {
        this.vm.getAnimalClassProperty().bind(this.animalClassComboBox.valueProperty());
        this.animalClassComboBox.getItems().addAll(AnimalClass.values());
        this.vm.getAnimalProperty().bind(this.subjectComboBox.valueProperty());
        this.heightLabel.textProperty().bind(this.vm.getHeightProperty().asString());
        this.weightLabel.textProperty().bind(this.vm.getWeightProperty().asString());
        this.lengthLabel.textProperty().bind(this.vm.getLengthProperty().asString());
        this.tagIDLabel.textProperty().bind(this.vm.getTagIDProperty().asString());
        this.descriptionTextArea.textProperty().bind(this.vm.getDescriptionProperty());
        this.projectNameLabel.textProperty().bind(this.vm.getProjectNameProperty());
    }
}

