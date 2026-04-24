package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidResponseException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private SimpleObjectProperty<LocalDate> date;
    private ObjectProperty<Integer> hour;
    private ObjectProperty<Integer> minute;
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
        this.date = new SimpleObjectProperty<LocalDate>(null);
        this.hour = new SimpleObjectProperty<Integer>(0);
        this.minute = new SimpleObjectProperty<Integer>(0);
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
    public SimpleObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    /**
     * Gets the Hour String Property.
     *
     * @return the hour String Property
     */
    public ObjectProperty<Integer> hourProperty() {
        return this.hour;
    }

    /**
     * Gets the Minute String Property.
     *
     * @return the minute String Property
     */
    public ObjectProperty<Integer> minuteProperty() {
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
     * Creates a Sighting object and sends a request to the server.
     *
     * @pre none
     * @post Sighting is saved in Server if Sighting is Valid and is Saved in Server
     * @return true if the object was Saved, false otherwise.
     */
    public boolean sendSighting() {
        try {
            int animalID = Integer.parseInt(this.animalIDProperty().get());
            var location = this.locationProperty().get();
            var latitude = Double.parseDouble(this.latitudeProperty().get());
            var longitude = Double.parseDouble(this.longitudeProperty().get());
            var formattedTime = this.formatDateAndTime();
            var note = this.noteProperty().get();
            var sighting = new Sighting(animalID, location, latitude, longitude, formattedTime, note, null);

            var request = new AddSightingRequest(this.authSession.getToken(), sighting);
            var response = this.serverService.addSighting(request);

            if (!response) {
                throw new InvalidResponseException("Server did not save the sighting");
            }
            return true;
        } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException(exc.getMessage());
        }
    }

    private LocalDateTime formatDateAndTime() {
        StringBuilder dateAndTime = new StringBuilder();
        dateAndTime.append(this.dateProperty().get());
        dateAndTime.append("T");
        if (this.hourProperty().get() < 10) {
            dateAndTime.append("0").append(this.hourProperty().get());
        } else {
            dateAndTime.append(this.hourProperty().get());
        }
        dateAndTime.append(":");
        if (this.minuteProperty().get() < 10) {
            dateAndTime.append("0").append(this.minuteProperty().get());
        } else {
            dateAndTime.append(this.minuteProperty().get());
        }
        var formattedTime = LocalDateTime.parse(dateAndTime);
        return formattedTime;
    }

    /**
     * Clears all the fields.
     */
    public void clearAllFields() {
        this.animalID.set("");
        this.location.set("");
        this.latitude.set("");
        this.longitude.set("");
        this.date.set(null);
        this.hour.set(0);
        this.minute.set(0);
        this.note.set("");
    }
}
