package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetAnimalsByType {

    @Test
    void testGetAnimalsByType() {
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        vm.getAnimalClassProperty().set(AnimalClass.BIRD);
        Animal animal = DataStorage.getAnimals().get(1);
        Project project = DataStorage.getProjects().get(1);
        vm.setProject(project);

        assertEquals(animal, vm.getAnimalsByType(vm.getAnimalClassProperty().get()).getFirst());
    }
}
