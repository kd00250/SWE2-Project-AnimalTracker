package edu.westga.cs3211.animaltracker.viewmodel.seeData;

import edu.westga.cs3211.animaltracker.model.Sighting;
import javafx.beans.property.*;

import java.time.LocalDateTime;

public class SightingRowViewModel {
    private final StringProperty location;
    private final DoubleProperty latitude;
    private final DoubleProperty longitude;
    private final ObjectProperty<LocalDateTime> time;
    private final StringProperty notes;


    public SightingRowViewModel(Sighting sighting) {
        if (sighting == null) {
            throw new IllegalArgumentException("Sighting cannot be null");
        }
        this.location = new SimpleStringProperty(sighting.getLocation());
        this.latitude = new SimpleDoubleProperty(sighting.getLatitude());
        this.longitude = new SimpleDoubleProperty(sighting.getLongitude());
        this.time = new SimpleObjectProperty<>(sighting.getTime());
        this.notes = new SimpleStringProperty(sighting.getNotes());
    }

    public StringProperty locationProperty() {
        return location;
    }


    public StringProperty notesProperty() {
        return notes;
    }

    public LocalDateTime getTime() {
        return time.get();
    }

    public ObjectProperty<LocalDateTime> timeProperty() {
        return time;
    }



    public DoubleProperty longitudeProperty() {
        return longitude;
    }

    public DoubleProperty latitudeProperty() {
        return latitude;
    }
}
