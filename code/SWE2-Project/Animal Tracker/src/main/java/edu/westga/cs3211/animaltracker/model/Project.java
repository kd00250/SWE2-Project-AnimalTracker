package edu.westga.cs3211.animaltracker.model;

import java.util.List;

/**
 * The Project Class.
 *
 * @author mrocker1
 */
public class Project {

    private final String name;
    private final List<Animal> animals;
    private final List<User> users;
    private int id;

    /**
     * Instantiates a new Project.
     *
     * @param users   the users
     * @param name    the name of the project
     * @param animals the animals in the project
     * @pre users != null && name != null or empty && animals != null
     * @post getUsers().equals(users) &&
     * getName.equals(name) &&
     * getAnimals.equals(animals) &&
     * getId = random project id.
     */
    public Project(List<User> users, String name, List<Animal> animals) {
        if (users == null) {
            throw new IllegalArgumentException("Users cannot be null");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (animals == null) {
            throw new IllegalArgumentException("Animals cannot be null");
        }
        this.name = name;
        this.animals = animals;
        this.users = users;
        this.id = DataStorage.getNextProjectId();
        DataStorage.getProjects().put(this.getId(), this);
    }

    /**
     * Instantiates a new Project.
     *
     * @param users   the users
     * @param name    the name of the project
     * @param animals the animals in the project
     * @param id the id of the user
     * @pre users != null && name != null or empty && animals != null
     * @post getUsers().equals(users) &&
     * getName.equals(name) &&
     * getAnimals.equals(animals) &&
     * getId = random project id.
     */
    public Project(List<User> users, String name, List<Animal> animals, int id) {
        this(users, name, animals);
        this.id = id;
        System.out.println("success");
    }

    /**
     * Gets the Name of the Project.
     *
     * @return the name of the Project
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the List of Animals.
     *
     * @return the List of Animals
     */
    public List<Animal> getAnimals() {
        return this.animals;
    }

    /**
     * Gets the List of Users.
     *
     * @return the List of Users
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Adds an animal to the List of Animals.
     *
     * @param animal the Animal
     * @pre animal != null && !animals.contains(animal)
     * @post animals.size() = animals.size()@prev + 1 &&
     * containsAnimal(animal) == true
     */
    public void addAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("animal cannot be null");
        }
        if (this.containsAnimal(animal)) {
            throw new IllegalArgumentException("animal already exists");
        }
        this.animals.add(animal);
    }

    /**
     * Checks if the Animal is in the List.
     *
     * @param animal the Animal
     * @return true if animal is in the List, false otherwise.
     * @pre animal != null
     * @post none
     */
    public boolean containsAnimal(Animal animal) {
        return this.animals.contains(animal);
    }

    /**
     * Checks if an AnimalClass is in the List.
     *
     * @param animalClass the animalClass
     * @return true if there is an animal of the AnimalClass, false otherwise
     * @pre animalClass != null
     * @post none
     */
    public boolean containsAnimalType(AnimalClass animalClass) {
        if (animalClass == null) {
            throw new IllegalArgumentException("animalClass cannot be null");
        }
        return this.animals.stream().anyMatch(animal -> animal.getAnimalClass().equals(animalClass));
    }

    /**
     * Returns a List of Animals of a specific Animal Class.
     *
     * @param animalClass The Animal Class
     * @return The List of Animals of an Animal Class.
     * @pre animalClass != null
     * @post none
     */
    public List<Animal> getAnimalsByType(AnimalClass animalClass) {
        if (animalClass == null) {
            throw new IllegalArgumentException("animalClass cannot be null");
        }
        return this.animals.stream().filter(animal -> animal.getAnimalClass().equals(animalClass)).toList();
    }

    /**
     * Adds a User to the Project.
     *
     * @param user the User
     * @pre user != null
     * @post users.size() = users.size()@prev + 1
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        if (this.containsUser(user)) {
            throw new IllegalArgumentException("user already exists");
        }
        this.users.add(user);
    }

    /**
     * Checks if the User is in the List.
     *
     * @param user the User
     * @return true if user is in the List, false otherwise.
     * @pre none
     * @post none
     */
    public boolean containsUser(User user) {
        return this.users.contains(user);
    }

    /**
     * Removes a User from the Project.
     *
     * @param user the User
     * @pre user != null
     * @post users.size() = users.size()@prev - 1 if user was in the list
     */
    public void removeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        if (!this.containsUser(user)) {
            throw new IllegalArgumentException("user does not exist in project");
        }
        this.users.remove(user);
    }

    /**
     * Gets the project id.
     *
     * @return the project id
     */
    public int getId() {
        return this.id;
    }
}