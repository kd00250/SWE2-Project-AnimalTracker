package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.SelectProjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Optional;

/**
 * The select project code behind.
 *
 * @author mrocker1
 */
public class SelectProjectCodeBehind {

    @FXML
    private ListView<Project> projectsListView;

    @FXML
    private Button viewProjectButton;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;

    private SelectProjectViewModel viewModel;

    /**
     * Instantiates a new select project code behind.
     *
     * @pre none
     * @post viewModel is initialized
     */
    public SelectProjectCodeBehind() {
        this.viewModel = new SelectProjectViewModel();
    }

    /**
     * Initializes the controller.
     * Called automatically by JavaFX after FXML loading.
     *
     * @pre FXML components are loaded
     * @post components are bound to view model
     */
    @FXML
    void initialize() {
        this.bindComponentsToViewModel();
        this.setupListView();
    }

    /**
     * Binds UI components to the view model properties.
     *
     * @pre viewModel is initialized
     * @post UI components are bound to view model properties
     */
    private void bindComponentsToViewModel() {
        this.projectsListView.itemsProperty().bind(this.viewModel.projectsProperty());

        this.viewModel.selectedProjectProperty().bind(
                this.projectsListView.getSelectionModel().selectedItemProperty()
        );

        this.viewProjectButton.disableProperty().bind(
                this.viewModel.selectedProjectProperty().isNull()
        );
        this.deleteProjectButton.disableProperty().bind(
                this.viewModel.selectedProjectProperty().isNull()
        );
    }

    /**
     * Sets up the ListView to display project names.
     *
     * @pre projectsListView is initialized
     * @post ListView displays project names in custom cells
     */
    private void setupListView() {
        this.projectsListView.setCellFactory(param -> new ListCell<Project>() {
            @Override
            protected void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                if (empty || project == null) {
                    this.setText(null);
                } else {
                    this.setText(project.getName());
                }
            }
        });
    }

    /**
     * Handles the view project button click.
     * Navigates to the ViewProjectData page with the selected project.
     *
     * @param event the action event
     * @pre a project is selected
     * @post navigates to project details view
     */
    @FXML
    void onViewProjectClick(ActionEvent event) {
        Project selectedProject = this.viewModel.getSelectedProject();
        if (selectedProject != null) {
            try {
                ViewProjectDataCodeBehind controller = ViewSwapper.loadPageFromStage(
                        PageInformation.VIEW_PROJECT_PATH,
                        this.projectsListView,
                        PageInformation.VIEW_PROJECT_TITLE
                );

                // Pass the selected project and session to the view project page
                controller.setProject(selectedProject);
                controller.setSession(
                        this.viewModel.getSession(),
                        this.viewModel.getServerService()
                );

            } catch (IOException e) {
                this.showErrorAlert("Failed to open project details: " + e.getMessage());
            }
        }
    }

    /**
     * Handles the delete project button click.
     * Prompts user for confirmation before deleting the selected project.
     *
     * @param event the action event
     * @pre a project is selected
     * @post project is deleted if user confirms
     */
    @FXML
    void onDeleteProjectClick(ActionEvent event) {
        Project selectedProject = this.viewModel.getSelectedProject();
        if (selectedProject != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Delete Project");
            confirmAlert.setHeaderText("Delete " + selectedProject.getName() + "?");
            confirmAlert.setContentText("Are you sure you want to delete this project? This action cannot be undone.");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = this.viewModel.deleteSelectedProject();
                if (success) {
                    this.showSuccessAlert("Project '" + selectedProject.getName() + "' deleted successfully.");
                } else {
                    this.showErrorAlert("Failed to delete project.");
                }
            }
        }
    }

    /**
     * Handles the refresh button click.
     * Reloads the project list from the server.
     *
     * @param event the action event
     * @pre none
     * @post project list is refreshed
     */
    @FXML
    void onRefreshClick(ActionEvent event) {
        this.viewModel.refreshProjects();
    }

    /**
     * Handles the back button click.
     * Navigates back to the landing page.
     *
     * @param event the action event
     * @pre none
     * @post navigates to landing page
     */
    @FXML
    void onBackClick(ActionEvent event) {
        try {
            LandingPageCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.LANDING_PATH,
                    this.backButton,
                    PageInformation.LANDING_TITLE
            );

            controller.setSession(
                    this.viewModel.getSession(),
                    this.viewModel.getServerService()
            );

        } catch (IOException e) {
            this.showErrorAlert("Failed to navigate back: " + e.getMessage());
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
    public void setSession(LoginResponse session, ServerService server) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        this.viewModel.setSession(session, server);
    }

    /**
     * Shows a success alert to the user.
     *
     * @param message the success message
     * @pre message != null
     * @post alert is displayed to user
     */
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows an error alert to the user.
     *
     * @param message the error message
     * @pre message != null
     * @post alert is displayed to user
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}