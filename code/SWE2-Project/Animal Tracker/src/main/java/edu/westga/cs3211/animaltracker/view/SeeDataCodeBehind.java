package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.viewmodel.SeeDataViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;

public class SeeDataCodeBehind {


    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Sighting, LocalDateTime> dateColumn;

    @FXML
    private TableColumn<Sighting, Double> latitudeColumn;

    @FXML
    private TableColumn<Sighting, String> locationColumn;

    @FXML
    private TableColumn<Sighting, Double> longitudeColumn;

    @FXML
    private TableColumn<Sighting, String> notesColumn;

    @FXML
    private TableView<Sighting> sightingTable;

    @FXML
    private TableColumn<Sighting, LocalDateTime> timeColumn;

    @FXML
    private TableColumn<Sighting, String> userColumn;


    private SeeDataViewModel vm;

    @FXML
    void initialize() {
        this.vm = new SeeDataViewModel();
        this.setupColumns();
        this.bindProperties();
    }

    private void bindProperties() {
        this.sightingTable.itemsProperty().bind(this.vm.sightings());
    }
    public void setSession(LoginResponse session, ServerService server) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }

        this.vm.setSession(session, server);
    }

    private void setupColumns() {
        this.dateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getTime()));
    }
}
