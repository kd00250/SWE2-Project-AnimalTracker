package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.SimpleStringProperty;

/**
 * The AddSightingViewModel class.
 *
 * @author mrocker1
 */
public class AddSightingViewModel {

    private LoginResponse authSession;
    private ServerService serverService;
    private SimpleStringProperty animalID;
    private SimpleStringProperty location;
    private SimpleStringProperty latitude;
    private SimpleStringProperty longitude;
    private SimpleStringProperty date;
    private SimpleStringProperty hour;
    private SimpleStringProperty minute;
    private SimpleStringProperty note;

    /**
     * Instantiates a new AddSightingViewModel.
     *
     * @pre none
     * @post animalIDProperty().get() == "" &&
     *       locationProperty().get() == "" &&
     *       latitudeProperty().get() == "" &&
     *       longitudeProperty().get() == "" &&
     *       dateProperty().get() == "" &&
     *       hourProperty().get() == "" &&
     *       minuteProperty().get() == "" &&
     *       noteProperty().get() == ""
     */
    public AddSightingViewModel() {
        this.animalID = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
        this.latitude = new SimpleStringProperty("");
        this.longitude = new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.hour = new SimpleStringProperty("");
        this.minute = new SimpleStringProperty("");
        this.note = new SimpleStringProperty("");
    }

    /**
     * Sets the session for this view model.
     *
     * @param session the user's session
     * @param server the server service
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
    }

    /**
     * Gets the session information.
     *
     * @return the session
     */
    public LoginResponse getSession() {
        return this.authSession;
    }

    /**
     * Gets the server service.
     *
     * @return the server service
     */
    public ServerService getServerService() {
        return this.serverService;
    }

    /**
     * Gets the Animal ID String Property.
     *
     * @return the animal ID String Property
     */
    public SimpleStringProperty animalIDProperty() {
        return this.animalID;
    }

    /**
     * Gets the Location String Property.
     *
     * @return the location String Property
     */
    public SimpleStringProperty locationProperty() {
        return this.location;
    }

    /**
     * Gets the Latitude String Property.
     *
     * @return the latitude String Property
     */
    public SimpleStringProperty latitudeProperty() {
        return this.latitude;
    }

    /**
     * Gets the Longitude String Property.
     *
     * @return the longitude String Property
     */
    public SimpleStringProperty longitudeProperty() {
        return this.longitude;
    }

    /**
     * Gets the Date String Property.
     *
     * @return the date String Property
     */
    public SimpleStringProperty dateProperty() {
        return this.date;
    }

    /**
     * Gets the Hour String Property.
     *
     * @return the hour String Property
     */
    public SimpleStringProperty hourProperty() {
        return this.hour;
    }

    /**
     * Gets the Minute String Property.
     *
     * @return the minute String Property
     */
    public SimpleStringProperty minuteProperty() {
        return this.minute;
    }

    /**
     * Gets the Note String Property.
     *
     * @return the note String Property
     */
    public SimpleStringProperty noteProperty() {
        return this.note;
    }

    /**
     * Clears all the fields.
     */
    public void clearAllFields() {
        this.animalID.set("");
        this.location.set("");
        this.latitude.set("");
        this.longitude.set("");
        this.date.set("");
        this.hour.set("");
        this.minute.set("");
        this.note.set("");
    }
}
