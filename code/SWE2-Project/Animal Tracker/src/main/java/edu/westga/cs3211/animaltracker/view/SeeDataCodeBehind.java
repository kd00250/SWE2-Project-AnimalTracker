package edu.westga.cs3211.animaltracker.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SeeDataCodeBehind {

    @FXML
    private ListView<?> animalsTags;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableView<?> sightingTable;

    @FXML
    private TableColumn<?, ?> submissionColumn;

}
