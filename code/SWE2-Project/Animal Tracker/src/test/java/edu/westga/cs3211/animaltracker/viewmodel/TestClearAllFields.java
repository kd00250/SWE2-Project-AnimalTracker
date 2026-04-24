package edu.westga.cs3211.animaltracker.viewmodel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestClearAllFields {

    @Test
    void testClearAllFields() {
        AddSightingViewModel vm = new AddSightingViewModel();
        vm.animalIDProperty().set("1234");
        vm.locationProperty().setValue("Forest");
        vm.latitudeProperty().setValue("13.0");
        vm.longitudeProperty().setValue("15.0");
        vm.dateProperty().setValue(LocalDate.of(2025, 1, 1));
        vm.hourProperty().setValue(10);
        vm.minuteProperty().setValue(15);
        vm.noteProperty().setValue("Saw it flying with a flock.");

        vm.clearAllFields();

        assertAll(
                () -> assertTrue(vm.animalIDProperty().getValue().isEmpty()),
                () -> assertTrue(vm.locationProperty().getValue().isEmpty()),
                () -> assertTrue(vm.longitudeProperty().getValue().isEmpty()),
                () -> assertTrue(vm.latitudeProperty().getValue().isEmpty()),
                () -> assertNull(vm.dateProperty().getValue()),
                () -> assertEquals(0, (int) vm.hourProperty().getValue()),
                () -> assertEquals(0, (int) vm.minuteProperty().getValue()),
                () -> assertTrue(vm.noteProperty().getValue().isEmpty())
        );
    }

}