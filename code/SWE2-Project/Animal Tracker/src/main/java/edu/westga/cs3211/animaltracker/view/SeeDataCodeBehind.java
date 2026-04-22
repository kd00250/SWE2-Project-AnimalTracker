package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SightingRowViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SeeDataCodeBehind {


    @FXML
    private Button backButton;

    @FXML
    private TableColumn<SightingRowViewModel, LocalDate> dateColumn;

    @FXML
    private TableColumn<SightingRowViewModel, Double> latitudeColumn;

    @FXML
    private TableColumn<SightingRowViewModel, String> locationColumn;

    @FXML
    private TableColumn<SightingRowViewModel, Double> longitudeColumn;

    @FXML
    private TableColumn<SightingRowViewModel, String> notesColumn;

    @FXML
    private TableView<SightingRowViewModel> sightingTable;

    @FXML
    private TableColumn<SightingRowViewModel, LocalTime> timeColumn;

    @FXML
    private TableColumn<SightingRowViewModel, String> userColumn;


    private SeeDataViewModel vm;

    @FXML
    void initialize() {
        this.vm = new SeeDataViewModel();
        this.setupColumns();

    }

    public void setAnimal(SimpleObjectProperty<Animal> animal) {
        this.vm.setAnimalProperty(animal);
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
        this.bindProperties();
    }

    private void setupColumns() {
        this.dateColumn.setCellValueFactory(cellData ->
                cellData.getValue().dateProperty());
        this.latitudeColumn.setCellValueFactory(cellData ->
                cellData.getValue().latitudeProperty().asObject());
        this.longitudeColumn.setCellValueFactory(cellData ->
                cellData.getValue().longitudeProperty().asObject());
        this.notesColumn.setCellValueFactory(cellData ->
                cellData.getValue().notesProperty());
        this.locationColumn.setCellValueFactory(cellData ->
                cellData.getValue().locationProperty());
        this.timeColumn.setCellValueFactory(cellData ->
                cellData.getValue().timeProperty());
    }
}
