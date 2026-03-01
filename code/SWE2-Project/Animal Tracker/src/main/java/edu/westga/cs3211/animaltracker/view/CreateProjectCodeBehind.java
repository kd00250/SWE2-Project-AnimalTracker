package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.CreateProjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * The CreateProject CodeBehind.
 *
 * @author mrocker1
 */
public class CreateProjectCodeBehind {

    @FXML
    private Label projectNameLabel;

    @FXML
    private TextField projectNameTextField;

    @FXML
    private Label projectNameWarningLabel;

    @FXML
    private Label projectLocationLabel;

    @FXML
    private TextField projectLocationTextField;

    @FXML
    private Label projectLocationWarningLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label scientistToAddLabel;

    //TODO Check to make sure that it will be Scientist/Correct Object
    @FXML
    private ListView<String> scientistToAddListView;

    @FXML
    private Button removeScientistToAddButton;

    @FXML
    private Label availableScientistLabel;

    //TODO Check to make sure that it will be Scientist/Correct Object
    @FXML
    private ListView<String> availableScientistToAddListView;

    @FXML
    private Button addScientistToProjectButton;

    @FXML
    private Button createProjectButton;

    private CreateProjectViewModel viewModel;


    /**
     * Instantiates a new CreateProjectCodeBehind.
     *
     * @pre none
     * @post none
     */
    public CreateProjectCodeBehind() {
        this.viewModel = new CreateProjectViewModel();
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Goes back to Landing Page.
     *
     * @param actionEvent the event
     */
    public void onBackButtonClick(ActionEvent actionEvent) {
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
            System.err.println(e.getMessage());
        }
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Handles when the Add Scientist button is clicked.
     *
     * @param actionEvent the event
     */
    public void onAddScientistToProjectClick(ActionEvent actionEvent) {
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Handles when the Remove Scientist button is clicked.
     *
     * @param actionEvent the event
     */
    public void onRemoveScientistToAddClick(ActionEvent actionEvent) {
    }

    //TODO Fix Documentation to how event will actually work

    /**
     * Handles when the Create Project button is clicked.
     *
     * @param actionEvent the event
     */
    public void onCreateProjectClick(ActionEvent actionEvent) {
    }

    /**
     * Sets the session for this page.
     * @param session the users session
     * @param server the server to be used
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.viewModel.setSession(session, server);
    }
}
