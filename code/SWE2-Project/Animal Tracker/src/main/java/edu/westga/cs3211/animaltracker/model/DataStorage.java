package edu.westga.cs3211.animaltracker.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * The data storage class.
 *
 * This class is for testing purpose only and is taking the place of a sever storing information
 */
public class DataStorage {
    private static ArrayList<User> users;
    private static HashMap<String, User> usernameMap;
    private static HashMap<String, User> tokenMap;
    private static HashMap<String, ZonedDateTime> expirationMap;
    private static HashMap<Integer, Animal> animals;
    private static HashMap<Integer, Project> projects;

    static {
        reset();
    }

    public static void addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("project cannot be null");
        }
        projects.put(project.getId(), project);
    }

    /**
     * gets the scientists.
     *
     * @return the scientists
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * finds out if the username is available or not.
     *
     * @param username the desired username
     * @return true or false if the username is already taken or not
     */
    public static Boolean isUsernameAvailable(String username) {
        boolean result = true;
        for (User user : getUsers()) {
            result = !user.getUsername().equals(username);
        }
        return result;
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
     * Gets the username map.
     *
     * @return the username map
     */
    public static HashMap<String, User> getUsernameMap() {
        return usernameMap;
    }

    /**
     * Gets the expiration date map.
     *
     * @return the expiration date map
     */
    public static HashMap<String, ZonedDateTime> getExpirationDateMap() {
        return expirationMap;
    }

    public static HashMap<String, User> getTokenMap() {
        return tokenMap;
    }

    public static HashMap<String, ZonedDateTime> getExpirationMap() {
        return expirationMap;
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

    public static void deleteProjectIfExist(int projectId) {
        projects.remove(projectId);
    }

    /**
     * generates the token for the user.
     *
     * @param user the user
     * @return the token for the user
     */
    public static String generateTokenForUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        var token = UUID.randomUUID().toString();
        tokenMap.put(token, user);
        expirationMap.put(token, ZonedDateTime.now(ZoneId.of("UTC")));
        return token;
    }

    /**
     * gets user by username.
     *
     * @param username the username.
     * @return the user
     */
    public static User getUserByUsername(String username) {
        return usernameMap.get(username);
    }

    /**
     * gets the scientist by token.
     *
     * @param token the token
     * @return the user
     */
    public static User getUserByToken(String token) {
        return tokenMap.get(token);
    }

    /**
     * Checks if a given token exist in the storage.
     *
     * @param token the token
     * @return true if token exist, otherwise false
     */
    public static boolean tokenExist(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Token cannot be null.");
        }
        return tokenMap.containsKey(token);
    }

    public static Animal getAnimalById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Animal ID cannot be negative.");
        }
        return animals.get(id);
    }

    /**
     * resets the collects (to be used in testing purposes only).
     */
    public static void reset() {
        users = new ArrayList<>();
        animals = new HashMap<>();
        projects = new HashMap<>();
        usernameMap = new HashMap<>();
        tokenMap = new HashMap<>();
        expirationMap = new HashMap<>();

        Animal defaultAnimal = new Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "");

        User defaultUser = new User("Bob", "1234", Role.SCIENTIST);
        DataStorage.generateTokenForUser(defaultUser);
        users.add(defaultUser);

        ArrayList<User> projectUsers = new ArrayList<>();
        projectUsers.add(defaultUser);

        ArrayList<Animal> projectAnimals = new ArrayList<>();
        projectAnimals.add(defaultAnimal);

        new Project("Wildlife Migration Study", new ArrayList<Scientist>(), projectAnimals, projectUsers);
    }
}
