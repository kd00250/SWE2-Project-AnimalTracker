package edu.westga.cs3211.animaltracker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The data storage class.
 *
 * This class is for testing purpose only and is taking the place of a sever storing information
 */
public class DataStorage {
    private static ArrayList<Scientist> scientists;
    private static HashMap<Integer, Animal> animals;
    private static HashMap<Integer, Project> projects;

    static {
        scientists = new ArrayList<>();
        animals = new HashMap<>();
        projects = new HashMap<>();

        Animal bird = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        animals.put(getNextAnimalId(), bird);
    }

    /**
     * gets the scientists.
     *
     * @return the scientists
     */
    public static ArrayList<Scientist> getScientists() {
        return scientists;
    }

    /**
     * Gets the animals.
     *
     * @return the animals
     */
    public static HashMap<Integer, Animal> getAnimals() {
        return animals;
    }

    /**
     * gets the project.
     *
     * @return the projects
     */
    public static HashMap<Integer, Project> getProjects() {
        return projects;
    }

    /**
     * gets the next highest animal id.
     *
     * @return the next highestID
     */
    public static int getNextAnimalId() {
        if (animals.isEmpty()) {
            return 1;
        }
        return Collections.max(animals.keySet()) + 1;
    }

    /**
     * gets the next highest project id.
     *
     * @return the next highestID
     */
    public static int getNextProjectId() {
        if (projects.isEmpty()) {
            return 1;
        }
        return Collections.max(projects.keySet()) + 1;
    }

    /**
     * resets the collects (to be used in testing purposes only).
     */
    public static void reset() {
        scientists = new ArrayList<>();
        animals = new HashMap<>();
        projects = new HashMap<>();

        Animal bird = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
        animals.put(getNextAnimalId(), bird);
    }
}
