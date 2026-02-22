package edu.westga.cs3211.animaltracker.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

/**
 * The data storage class.
 *
 * This class is for testing purpose only and is taking the place of a sever storing information
 */
public class DataStorage {
    private static ArrayList<Scientist> scientists;
    private static HashMap<String, User> userUsernameMap;
    private static HashMap<String, User> userTokenMap;
    private static HashMap<String, ZonedDateTime> expirationMap;
    private static HashMap<Integer, Animal> animals;
    private static HashMap<Integer, Project> projects;

    static {
        reset();
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
     * generates the token for the user.
     * @param user the user
     * @return the token for the user
     */
    public static String generateTokenForUser(User user) {
        var token = UUID.randomUUID().toString();
        userTokenMap.put(token, user);
        expirationMap.put(token, ZonedDateTime.now(ZoneId.of("UTC")));
        return token;
    }

    /**
     * gets user by username.
     * @param username the username.
     * @return the user
     */
    public static User getUserByUsername(String username) {
        return userUsernameMap.get(username);
    }

    /**
     * gets the scientist by token.
     * @param token the token
     * @return the user
     */
    public static User getScientistByToken(String token) {
        return userTokenMap.get(token);
    }

    /**
     * resets the collects (to be used in testing purposes only).
     */
    public static void reset() {
        scientists = new ArrayList<>();
        animals = new HashMap<>();
        projects = new HashMap<>();
        userUsernameMap = new HashMap<>();
        userTokenMap = new HashMap<>();
        expirationMap = new HashMap<>();

        new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");
    }
}
