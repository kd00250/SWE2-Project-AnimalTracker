package edu.westga.cs3211.animaltracker.viewmodel.seeData;

import edu.westga.cs3211.animaltracker.model.Sighting;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SightingRowViewModel {
    private final StringProperty location;
    private final DoubleProperty latitude;
    private final DoubleProperty longitude;
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<LocalTime> time;
    private final StringProperty notes;


    public SightingRowViewModel(Sighting sighting) {
        if (sighting == null) {
            throw new IllegalArgumentException("Sighting cannot be null");
        }
        this.location = new SimpleStringProperty(sighting.getLocation());
        this.latitude = new SimpleDoubleProperty(sighting.getLatitude());
        this.longitude = new SimpleDoubleProperty(sighting.getLongitude());
        this.time = new SimpleObjectProperty<>(sighting.getTime().toLocalTime());
        this.date = new SimpleObjectProperty<>(sighting.getTime().toLocalDate());
        this.notes = new SimpleStringProperty(sighting.getNotes());
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
}
