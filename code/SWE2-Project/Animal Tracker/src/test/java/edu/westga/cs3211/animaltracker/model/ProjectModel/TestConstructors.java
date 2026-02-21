package edu.westga.cs3211.animaltracker.model.ProjectModel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.Scientist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestConstructors {

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Project(null, new ArrayList<Scientist>(), new ArrayList<Animal>()));
    }

    @Test
    void testEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Project("", new ArrayList<Scientist>(), new ArrayList<Animal>()));
    }

    @Test
    void testBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Project(" ", new ArrayList<Scientist>(), new ArrayList<Animal>()));
    }

    @Test
    void testNullScientists() {
        assertThrows(IllegalArgumentException.class, () -> new Project("Salmon Migration", null, new ArrayList<Animal>()));
    }

    @Test
    void testNullAnimals() {
        assertThrows(IllegalArgumentException.class, () -> new Project("Salmon Migration", new ArrayList<Scientist>(), null));
    }

    @Test
    void testDefaultConstructor() {
        var project = new Project();

        assertAll(
                () -> {
                    assertEquals("Test", project.getName());
                    assertNotNull(project.getScientists());
                    assertNotNull(project.getAnimals());
                }
        );
    }

    @Test
    void testConstructorOnlyName() {
        var project = new Project("Salmon Migration");

        assertAll(
                () -> {
                    assertEquals("Salmon Migration", project.getName());
                    assertNotNull(project.getScientists());
                    assertNotNull(project.getAnimals());
                }
        );
    }

    @Test
    void testConstructorWithNameAndEmptyScientists() {
        var scientists = new ArrayList<Scientist>();
        var project = new Project("Salmon Migration", scientists);

        assertAll(
                () -> {
                    assertEquals("Salmon Migration", project.getName());
                    assertEquals(scientists, project.getScientists());
                    assertNotNull(project.getAnimals());
                }
        );
    }

    @Test
    void testThreeParameterConstructor() {
        var scientists = new ArrayList<Scientist>();
        var animals = new ArrayList<Animal>();
        var project = new Project("Salmon Migration", scientists, animals);

        assertAll(
                () -> {
                    assertEquals("Salmon Migration", project.getName());
                    assertEquals(scientists, project.getScientists());
                    assertEquals(animals, project.getAnimals());
                }
        );
    }

    @Test
    void testConstructorWithScientists() {
        var scientists = new ArrayList<Scientist>();
        var scientist1 = new Scientist("John Doe", "123456789");
        var scientist2 = new Scientist("Sallie Mae", "234567891");
        var scientist3 = new Scientist("Jane Doe", "3456789012");
        scientists.add(scientist1);
        scientists.add(scientist2);
        scientists.add(scientist3);
        var animals = new ArrayList<Animal>();
        var project = new Project("Salmon Migration", scientists, animals);

        assertAll(
                () -> {
                    assertEquals("Salmon Migration", project.getName());
                    assertEquals(scientists, project.getScientists());
                    assertEquals(scientist1, project.getScientists().get(0));
                    assertEquals(scientist2, project.getScientists().get(1));
                    assertEquals(scientist3, project.getScientists().get(2));
                    assertEquals(animals, project.getAnimals());
                }
        );
    }

}