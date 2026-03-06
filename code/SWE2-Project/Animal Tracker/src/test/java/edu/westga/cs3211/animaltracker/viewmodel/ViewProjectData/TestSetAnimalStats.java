package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSetAnimalStats {

    @Test
    void testSetAnimalStats() {
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        Animal animal = DataStorage.getAnimals().get(1);
        vm.getAnimalProperty().set(animal);
        vm.setAnimalStats(animal);

        assertEquals(vm.getAnimalProperty().get(), animal);
        assertEquals(vm.getHeightProperty().get(), animal.getHeight());
        assertEquals(vm.getWeightProperty().get(), animal.getWeight());
        assertEquals(vm.getLengthProperty().get(), animal.getLength());
        assertEquals(vm.getTagIDProperty().get(), animal.getTagID());
        assertEquals(vm.getDescriptionProperty().get(), animal.getDescription());
    }
}
