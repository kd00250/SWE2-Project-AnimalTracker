package edu.westga.cs3211.animaltracker.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Project Class.
 * @author mrocker1
 */
public class Project {

    private String name;
    private List<Scientist> scientists;
    private List<Animal> animals;
    private List<User> users;
    private int id;

    /**
     * Instantiates a new Project.
     *
     * @pre none
     * @post getName().equals(name) &&
     *       getScientists().equals(scientists) &&
     *       getAnimals().equals(animals) &&
     *       getUsers().equals(users)
     */
    public Project() {
        this("Test", new ArrayList<Scientist>(), new ArrayList<Animal>(), new ArrayList<User>());
    }
    /**
     * Instantiates a new Project.
     *
     * @pre name != null && !name.isBlank()
     * @post getName().equals(name) &&
     *       getScientists().equals(scientists) &&
     *       getAnimals().equals(animals) &&
     *       getUsers().equals(users)
     *
     * @param name the Name of the Project
     */
    public Project(String name) {
        this(name, new ArrayList<Scientist>(), new ArrayList<Animal>(), new ArrayList<User>());
    }

    public Project(List<User> users, String name, List<Animal> animals) {
        this.name = name;
        this.scientists = new ArrayList<>();
        this.animals = animals;
        this.users = users;
        this.id = DataStorage.getNextProjectId();
        DataStorage.getProjects().put(this.getId(), this);
    }
    /**
     * Instantiates a new Project.
     *
     * @pre name != null && !name.isBlank() &&
     *      scientist != null
     * @post getName().equals(name) &&
     *       getScientists().equals(scientists) &&
     *       getAnimals().equals(animals) &&
     *       getUsers().equals(users)
     *
     * @param name the Name of the Project
     * @param scientists the Scientists
     */
    public Project(String name, List<Scientist> scientists) {
        this(name, scientists, new ArrayList<Animal>(), new ArrayList<User>());
    }

    /**
     * Instantiates a new Project.
     *
     * @pre name != null && !name.isBlank() &&
     *      scientist != null &&
     *      animals != null
     * @post getName().equals(name) &&
     *       getScientists().equals(scientists) &&
     *       getAnimals().equals(animals) &&
     *       getUsers().equals(users)
     *
     * @param name the Name of the Project
     * @param scientists the Scientists
     * @param animals the Animals
     */
    public Project(String name, List<Scientist> scientists, List<Animal> animals) {
        this(name, scientists, animals, new ArrayList<User>());
    }

    /**
     * Instantiates a new Project.
     *
     * @pre name != null && !name.isBlank() &&
     *      scientist != null &&
     *      animals != null &&
     *      users != null
     * @post getName().equals(name) &&
     *       getScientists().equals(scientists) &&
     *       getAnimals().equals(animals) &&
     *       getUsers().equals(users)
     *
     * @param name the Name of the Project
     * @param scientists the Scientists
     * @param animals the Animals
     * @param users the Users
     */
    public Project(String name, List<Scientist> scientists, List<Animal> animals, List<User> users) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (scientists == null) {
            throw new IllegalArgumentException("scientists cannot be null");
        }
        if (animals == null) {
            throw new IllegalArgumentException("animals cannot be null");
        }
        if (users == null) {
            throw new IllegalArgumentException("users cannot be null");
        }
        this.name = name;
        this.scientists = scientists;
        this.animals = animals;
        this.users = users;
        this.id = DataStorage.getNextProjectId();
        DataStorage.getProjects().put(this.getId(), this);
    }

    /**
     * Gets the Name of the Project.
     * @return the name of the Project
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the List of Animals.
     * @return the List of Animals
     */
    public List<Animal> getAnimals() {
        return this.animals;
    }

    /**
     * Gets the List of Scientist.
     * @return the List of Scientist
     */
    public List<Scientist> getScientists() {
        return this.scientists;
    }

    /**
     * Gets the List of Users.
     * @return the List of Users
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Adds a Scientist to the Project.
     *
     * @pre scientist != null
     * @post scientists.size() = scientists.size()@prev + 1
     *
     * @param scientist the Scientist
     */
    public void addScientist(Scientist scientist) {
        if (scientist == null) {
            throw new IllegalArgumentException("scientist cannot be null");
        }
        if (this.containsScientist(scientist)) {
            throw new IllegalArgumentException("scientist already exists");
        }
        this.scientists.add(scientist);
    }

    /**
     * Checks if the Scientist is in the List.
     *
     * @pre none
     * @post none
     *
     * @param scientist the Animal
     * @return true if scientist is in the List, false otherwise.
     */
    public boolean containsScientist(Scientist scientist) {
        return this.scientists.contains(scientist);
    }

    /**
     * Adds an animal to the List of Animals.
     * @pre animal != null && !animals.contains(animal)
     * @post animals.size() = animals.size()@prev + 1 &&
     *       containsAnimal(animal) == true
     * @param animal the Animal
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
     * @pre animal != null
     * @post none
     *
     * @param animal the Animal
     * @return true if animal is in the List, false otherwise.
     */
    public boolean containsAnimal(Animal animal) {
        return this.animals.contains(animal);
    }

    /**
     * Checks if an AnimalClass is in the List.
     *
     * @pre animalClass != null
     * @post none
     *
     * @param animalClass the animalClass
     * @return true if there is an animal of the AnimalClass, false otherwise
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
     * @pre animalClass != null
     * @post none
     *
     * @param animalClass The Animal Class
     * @return The List of Animals of an Animal Class.
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
     * @pre user != null
     * @post users.size() = users.size()@prev + 1
     *
     * @param user the User
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
     * @pre none
     * @post none
     *
     * @param user the User
     * @return true if user is in the List, false otherwise.
     */
    public boolean containsUser(User user) {
        return this.users.contains(user);
    }

    /**
     * Removes a User from the Project.
     *
     * @pre user != null
     * @post users.size() = users.size()@prev - 1 if user was in the list
     *
     * @param user the User
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