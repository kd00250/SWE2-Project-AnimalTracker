package edu.westga.cs3211.animaltracker.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The data storage class.
 */
public class DataStorage {
    private static ArrayList<Scientist> scientists;
    private static HashMap<Integer, Animal> animals;
    private static HashMap<Integer, Project> projects;

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
}
