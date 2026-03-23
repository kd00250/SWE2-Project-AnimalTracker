package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestConstructors {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Project(new ArrayList<>(), null, new ArrayList<>()));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Project(new ArrayList<>(), "", new ArrayList<>()));
    }

    @Test
    void testBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Project(new ArrayList<>(), " ", new ArrayList<>()));
    }


    @Test
    void testNullAnimals() {
        assertThrows(IllegalArgumentException.class, () -> new Project(new ArrayList<>(), "test ", null));
    }


    @Test
    void testThreeParameterConstructor() {
        var users = new ArrayList<User>();
        var animals = new ArrayList<Animal>();
        var project = new Project(users , "Salmon Migration", animals);

        assertAll(
                () -> {
                    assertEquals("Salmon Migration", project.getName());
                    assertEquals(users, project.getUsers());
                    assertEquals(animals, project.getAnimals());
                }
        );
    }

    @Test
    void testConstructorWithScientists() {
        var users = new ArrayList<User>();
        var user1 = new User("John Doe", "123456789", Role.SCIENTIST);
        var user2 = new User("Sallie Mae", "234567891", Role.SCIENTIST);
        var user3 = new User("Jane Doe", "3456789012", Role.SCIENTIST);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        var animals = new ArrayList<Animal>();
        var project = new Project(users, "Salmon Migration", animals);

        assertAll(
                () -> {
                    assertEquals("Salmon Migration", project.getName());
                    assertEquals(users, project.getUsers());
                    assertEquals(user1, project.getUsers().get(0));
                    assertEquals(user2, project.getUsers().get(1));
                    assertEquals(user3, project.getUsers().get(2));
                    assertEquals(animals, project.getAnimals());
                }
        );
    }


    @Test
    void testConstructorWithEmptyUsers() {
        List<Animal> animals = new ArrayList<>();
        List<User> users = new ArrayList<>();

        Project project = new Project(users, "Test Project", animals);

        assertNotNull(project.getUsers(), "checking users list is not null");
        assertEquals(0, project.getUsers().size(), "checking users list is empty");
    }


    @Test
    void testUsersNullInTripleParameter() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Project(null, "1234", new ArrayList<Animal>());
        });
    }

    @Test
    void testNameNullInTripleParameter() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Project(new ArrayList<User>(), null, new ArrayList<Animal>());
        });
    }

    @Test
    void testAnimalNullInTripleParameter() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Project(new ArrayList<User>(), "1234", null);
        });
    }

    @Test
    void testNameEmptyInTripleParameter() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Project(new ArrayList<User>(), "", new ArrayList<Animal>());
        });
    }

    @Test
    void testValidTripleParameterConstructor() {
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
        assertEquals(users, project.getUsers());
        assertEquals(animals, project.getAnimals());
        assertEquals(name, project.getName());
    }



}