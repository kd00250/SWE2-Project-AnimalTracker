package edu.westga.cs3211.animaltracker.model.server.service.localserver;

import edu.westga.cs3211.animaltracker.model.*;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestAddProject {
    @Test
    void testNullRequestThrows() {
        var server = new LocalServer();
        assertThrows(IllegalArgumentException.class, () -> {
            server.AddProject(null);
        });
    }

    @Test
    void testAddProjectCorrectlyAdds() {
        DataStorage.getProjects().clear();
        DataStorage.getAnimals().clear();
        DataStorage.getUsers().clear();
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

        var baseUser = new User("1234", "1234", Role.SCIENTIST);
        var animalId = project.getAnimals().stream().map(Animal::getId).collect(Collectors.toCollection(ArrayList::new));
        var scientistNames = project.getUsers().stream().map(User::username).collect(Collectors.toCollection(ArrayList::new));
        var request = new AddProjectRequest(project.getName(), scientistNames, animalId);
        var server = new LocalServer();
        server.AddProject(request);
        for (var currProject : DataStorage.getProjects().values()) {
            for (var scientist : currProject.getUsers()) {
                assertTrue(scientistNames.contains(scientist.username()));
            }
            for (var currAnimal : currProject.getAnimals()) {
                assertTrue(animalId.contains(currAnimal.getId()));
            }
            assertEquals(project.getName(), currProject.getName());
        }



    }

    @Test
    void testAddProjectTwoCorrectlyAdds() {
        DataStorage.getProjects().clear();
        DataStorage.getAnimals().clear();
        DataStorage.getUsers().clear();
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

        var animalId = project.getAnimals().stream().map(Animal::getId).collect(Collectors.toCollection(ArrayList::new));
        var scientistNames = project.getUsers().stream().map(User::username).collect(Collectors.toCollection(ArrayList::new));
        var request = new AddProjectRequest(project.getName(), scientistNames, animalId);
        var server = new LocalServer();
        server.AddProject(request);
        for (var currProject : DataStorage.getProjects().values()) {
            for (var scientist : currProject.getUsers()) {
                assertTrue(scientistNames.contains(scientist.username()));
            }
            for (var currAnimal : currProject.getAnimals()) {
                assertTrue(animalId.contains(currAnimal.getId()));
            }
            assertEquals(project.getName(), currProject.getName());
        }

    }
}
