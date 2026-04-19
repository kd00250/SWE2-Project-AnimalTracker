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
    private ListView<AnimalClass> animalClassListView;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label heightLabel;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label projectNameLabel;

    @FXML
    private ListView<Animal> animalListView;

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
    void onSightingDataClick(ActionEvent event) {
        if (this.animalListView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                SeeDataCodeBehind controller = ViewSwapper.loadPageFromStage(
                        PageInformation.SEE_DATA_PATH,
                        this.visualizeDataButton,
                        PageInformation.SEE_DATA_TITLE
                );
                controller.setSession(this.vm.getSession(), this.vm.getServerService());
            } catch (IOException e) {
                this.displayErrorPopup("Failed to navigate to sighting data: " + e.getMessage());
                e.printStackTrace();
            }
        }
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
        this.setUpListeners();
    }

    private void setUpListeners() {
        this.animalClassListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            this.animalListView.getItems().clear();
            this.vm.clearAnimalStats();

            if (newValue != null) {
                this.animalListView.getItems().addAll(this.vm.getAnimalsByType(newValue));
            }
        });

        this.animalListView.getSelectionModel().selectedItemProperty().addListener((obs, oldAnimal, newAnimal) -> {
            if (newAnimal != null) {
                this.vm.setAnimalStats(newAnimal);
            }
        });
    }

    private void setUpBindings() {
        this.animalListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.animalClassListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.vm.getAnimalClassProperty().bind(this.animalClassListView.getSelectionModel().selectedItemProperty());
        this.animalClassListView.getItems().addAll(AnimalClass.values());
        this.vm.getAnimalProperty().bind(this.animalListView.getSelectionModel().selectedItemProperty());
        this.heightLabel.textProperty().bind(this.vm.getHeightProperty().asString());
        this.weightLabel.textProperty().bind(this.vm.getWeightProperty().asString());
        this.lengthLabel.textProperty().bind(this.vm.getLengthProperty().asString());
        this.tagIDLabel.textProperty().bind(this.vm.getTagIDProperty().asString());
        this.descriptionTextArea.textProperty().bind(this.vm.getDescriptionProperty());
        this.projectNameLabel.textProperty().bind(this.vm.getProjectNameProperty());
    }
}

