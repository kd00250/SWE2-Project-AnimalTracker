package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SeeDataViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SightingRowViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The see data code behind responsible for seeing data.
 */
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
    private ViewProjectDataViewModel projectVM;

    @FXML
    void initialize() {
        this.vm = new SeeDataViewModel();
        this.setupColumns();

    }

    @FXML
    void onBackClick(ActionEvent event) {
        try {
            ViewProjectDataCodeBehind controller = ViewSwapper.loadPageFromStage(
                    PageInformation.VIEW_PROJECT_PATH,
                    this.backButton,
                    PageInformation.VIEW_PROJECT_TITLE
            );
            controller.setProject(this.projectVM.refreshProject());
            System.out.println(controller);
            controller.setSession(
                    this.vm.getSession(),
                    this.vm.getServerService()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the animal to retrieve sighting information.
     *
     * @param animal the animal property to set
     */
    public void setAnimal(SimpleObjectProperty<Animal> animal) {
        this.vm.setAnimalProperty(animal);
    }

    private void bindProperties() {
        this.sightingTable.itemsProperty().bind(this.vm.sightings());
    }

    /**
     * Sets the session information for the code behind.
     *
     * @param session   the session information
     * @param server    the server service
     * @param projectVM the project page viewmodel
     */
    public void setSession(LoginResponse session, ServerService server, ViewProjectDataViewModel projectVM) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        if (projectVM == null) {
            throw new IllegalArgumentException("ViewProjectDataViewModel cannot be null");
        }

        this.vm.setSession(session, server);
        this.projectVM = projectVM;
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
        this.userColumn.setCellValueFactory(cellData ->
                cellData.getValue().usernameProperty());
    }
}
