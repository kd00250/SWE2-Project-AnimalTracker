package edu.westga.cs3211.animaltracker.viewmodel.seeData.SightingRowViewmodel;

import edu.westga.cs3211.animaltracker.model.Sighting;
import edu.westga.cs3211.animaltracker.viewmodel.seeData.SightingRowViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestProperties {
    @Test
    public void testCreateValidSightingRow() {
        var dateTime = LocalDateTime.of(2024, 4, 20,10, 40);
        var sighting0 = new Sighting(10,
                "Florida", 10.6, 60.6,dateTime
                ,
                "Notes", "Bob");
        var vm = new SightingRowViewModel(sighting0);
        assertEquals("Florida", vm.locationProperty().getValue());
        assertEquals(10.6, vm.latitudeProperty().getValue(), 0.01);
        assertEquals(60.6, vm.longitudeProperty().getValue(), 0.01);
        assertEquals(dateTime.toLocalTime(), vm.timeProperty().getValue());
        assertEquals(dateTime.toLocalDate(), vm.dateProperty().getValue());
        assertEquals("Notes", vm.notesProperty().getValue());
        assertEquals("Bob", vm.usernameProperty().getValue());
    }

    @Test
    public void testCreateWithNullSighting() {

        assertThrows(IllegalArgumentException.class, () -> {
            new SightingRowViewModel(null);
        });
    }
}
