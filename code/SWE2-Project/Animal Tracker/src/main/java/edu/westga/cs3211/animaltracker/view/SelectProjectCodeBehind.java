package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SelectProjectCodeBehind {

    @FXML
    private Button viewProjectButton;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private Button backButton;

    @FXML
    private ListView<Project> projectsListView;

    @FXML
    void initialize() {

    }

    @FXML
    void onBackClick() {

    }

    public void onViewProjectClick(ActionEvent actionEvent) {
    }

    public void onDeleteProjectClick(ActionEvent actionEvent) {
    }

    public void onRefreshButton_Click(ActionEvent actionEvent) {
    }
}
