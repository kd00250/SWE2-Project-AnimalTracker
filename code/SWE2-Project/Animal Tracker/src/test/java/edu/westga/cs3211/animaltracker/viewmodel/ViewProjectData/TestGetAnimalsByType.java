package edu.westga.cs3211.animaltracker.viewmodel.ViewProjectData;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGetAnimalsByType {

    @Test
    void testGetAnimalsByType() {
        DataStorage.getAnimals().clear();
        DataStorage.getProjects().clear();
        var project = new Project(new ArrayList<>(), "test", new ArrayList<>());
        var animal = new Animal(AnimalClass.BIRD, 1, 1, 1, 111, "hi");
        project.addAnimal(animal);
        DataStorage.addProject(project);
        ViewProjectDataViewModel vm = new ViewProjectDataViewModel();
        vm.getAnimalClassProperty().set(AnimalClass.BIRD);
        vm.setProject(project);

        assertEquals(animal, vm.getAnimalsByType(vm.getAnimalClassProperty().get()).getFirst());
    }
}
