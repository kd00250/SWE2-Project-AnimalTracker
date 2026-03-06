package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeleteProjectIfExist {

    @Test
    void projectIsDeletedAndExist() {
        List<Animal> animals = new ArrayList<>();
        List<User> users = new ArrayList<>();
        var name = "Project1";
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        Animal animal1 = new Animal(AnimalClass.BIRD, 14, 24, 14, 14, "Bird");
        Animal animal2 = new Animal(AnimalClass.MAMMAL, 16, 24, 14, 11, "Cat");
        animals.add(animal1);
        animals.add(animal2);
        users.add(user1);
        users.add(user2);

        Project project = new Project(users, name, animals);
        var projectId = project.getId();

        DataStorage.getProjects().clear();
        DataStorage.addProject(project);
        var projectSizeWhenAdded = DataStorage.getProjects().size();
        DataStorage.deleteProjectIfExist(projectId);
        var deletedSize = DataStorage.getProjects().size();
        assertAll(() -> {
            assertEquals(1, projectSizeWhenAdded);
        }, () -> {
            assertEquals(0, deletedSize);
        });
    }

    @Test
    void testIdDoesNotMatch() {
        List<Animal> animals = new ArrayList<>();
        List<User> users = new ArrayList<>();
        var name = "Project1";
        User user1 = new User("user1", "password1", Role.SCIENTIST);
        User user2 = new User("user2", "password2", Role.CONTRIBUTOR);
        Animal animal1 = new Animal(AnimalClass.BIRD, 14, 24, 14, 14, "Bird");
        Animal animal2 = new Animal(AnimalClass.MAMMAL, 16, 24, 14, 11, "Cat");
        animals.add(animal1);
        animals.add(animal2);
        users.add(user1);
        users.add(user2);

        Project project = new Project(users, name, animals);
        var projectId = project.getId();

        DataStorage.getProjects().clear();
        DataStorage.addProject(project);
        var projectSizeWhenAdded = DataStorage.getProjects().size();
        DataStorage.deleteProjectIfExist(-50000);
        var deletedSize = DataStorage.getProjects().size();
        assertAll(() -> {
            assertEquals(1, projectSizeWhenAdded);
        }, () -> {
            assertEquals(1, deletedSize);
        });
    }
}
