package edu.westga.cs3211.animaltracker.viewmodel.seeData;

import edu.westga.cs3211.animaltracker.model.Sighting;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The sighting row view model, responsible as an intermediary between
 * the model sightings and properties required for the view, particularly rows in a table.
 */
public class SightingRowViewModel {
    private final Sighting sighting;
    private StringProperty location;
    private DoubleProperty latitude;
    private DoubleProperty longitude;
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> time;
    private StringProperty username;
    private StringProperty notes;

    /**
     * Instantiates a new row view model with sighting information.
     * @param sighting the sighting to populate properties with
     */
    public SightingRowViewModel(Sighting sighting) {
        if (sighting == null) {
            throw new IllegalArgumentException("Sighting cannot be null");
        }
        this.sighting = sighting;
        this.bindProperties();
    }

    private void bindProperties() {
        this.location = new SimpleStringProperty(this.sighting.getLocation());
        this.latitude = new SimpleDoubleProperty(this.sighting.getLatitude());
        this.longitude = new SimpleDoubleProperty(this.sighting.getLongitude());
        this.time = new SimpleObjectProperty<>(this.sighting.getTime().toLocalTime());
        this.date = new SimpleObjectProperty<>(this.sighting.getTime().toLocalDate());
        this.notes = new SimpleStringProperty(this.sighting.getNotes());
        this.username = new SimpleStringProperty(this.sighting.getUsername());
    }

    /**
     * Gets the location property of the sighting.
     * @return the location property
     */
    public StringProperty locationProperty() {
        return this.location;
    }

    /**
     * Gets the notes property of the sighting.
     * @return the notes property
     */
    public StringProperty notesProperty() {
        return this.notes;
    }

    /**
     * Gets the date property of the sighting.
     * @return the date property
     */
    public ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    /**
     * Gets the time property of the sighting.
     * @return the sighting property
     */
    public ObjectProperty<LocalTime> timeProperty() {
        return this.time;
    }

    /**
     * Gets the longitude property of the sighting.
     * @return the longitude property
     */
    public DoubleProperty longitudeProperty() {
        return this.longitude;
    }

    /**
     * Gets the latitude property of the sighting.
     * @return the latitude property
     */
    public DoubleProperty latitudeProperty() {
        return this.latitude;
    }

    /**
     * Gets the username property of the sighting.
     * @return the username property
     */
    public StringProperty usernameProperty() {
        return this.username;
    }
}
