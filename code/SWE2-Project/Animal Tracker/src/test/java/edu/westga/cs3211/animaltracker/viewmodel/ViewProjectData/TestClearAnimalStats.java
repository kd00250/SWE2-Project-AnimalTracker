package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestClearAnimalStats {

    @Test
    void testClearAnimalStats() {
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        Animal animal = DataStorage.getAnimals().get(1);
        vm.setAnimalStats(animal);
        vm.clearAnimalStats();

        assertEquals(0.0, vm.getHeightProperty().get());
        assertEquals(0.0, vm.getWeightProperty().get());
        assertEquals(0.0, vm.getLengthProperty().get());
        assertEquals(0, vm.getTagIDProperty().get());
        assertEquals("", vm.getDescriptionProperty().get());
    }
}
