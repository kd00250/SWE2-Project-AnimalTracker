package edu.westga.cs3211.animaltracker.viewmodel.seeData;

import edu.westga.cs3211.animaltracker.model.Sighting;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SightingRowViewModel {
    private final Sighting sighting;
    private StringProperty location;
    private DoubleProperty latitude;
    private DoubleProperty longitude;
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalTime> time;
    private StringProperty username;
    private StringProperty notes;


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
        this.longitude = new SimpleDoubleProperty(sighting.getLongitude());
        this.time = new SimpleObjectProperty<>(this.sighting.getTime().toLocalTime());
        this.date = new SimpleObjectProperty<>(this.sighting.getTime().toLocalDate());
        this.notes = new SimpleStringProperty(this.sighting.getNotes());
        this.username = new SimpleStringProperty(this.sighting.getUsername());
    }

    public StringProperty locationProperty() {
        return location;
    }


    public StringProperty notesProperty() {
        return notes;
    }



    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }


    public ObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public DoubleProperty longitudeProperty() {
        return longitude;
    }

    public DoubleProperty latitudeProperty() {
        return latitude;
    }
    public StringProperty usernameProperty() {
        return username;
    }
}
