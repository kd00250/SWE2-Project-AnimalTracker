package edu.westga.cs3211.animaltracker.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Project Class.
 *
 * @author mrocker1
 */
public class Project {

    private String name;
    private List<Animal> animals;
    private List<User> users;
    private int id;

    /**
     * Instantiates a new Project.
     *
     * @pre none
     * @post getName().equals(name) &&
     * getScientists().equals(scientists) &&
     * getAnimals().equals(animals) &&
     * getUsers().equals(users)
     */
    public Project() {
        this("Test", new ArrayList<Scientist>(), new ArrayList<Animal>(), new ArrayList<User>());
    }

    /**
     * Instantiates a new Project.
     *
     * @param name the Name of the Project
     * @pre name != null && !name.isBlank()
     * @post getName().equals(name) &&
     * getScientists().equals(scientists) &&
     * getAnimals().equals(animals) &&
     * getUsers().equals(users)
     */
    public Project(String name) {
        this(name, new ArrayList<Scientist>(), new ArrayList<Animal>(), new ArrayList<User>());
    }

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

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (animals == null) {
            throw new IllegalArgumentException("Animals cannot be null");
        }
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
     * @param name       the Name of the Project
     * @param scientists the Scientists
     * @pre name != null && !name.isBlank() &&
     * scientist != null
     * @post getName().equals(name) &&
     * getScientists().equals(scientists) &&
     * getAnimals().equals(animals) &&
     * getUsers().equals(users)
     */
    public Project(String name, List<Scientist> scientists) {
        this(name, scientists, new ArrayList<Animal>(), new ArrayList<User>());
    }

    /**
     * Instantiates a new Project.
     *
     * @param name       the Name of the Project
     * @param scientists the Scientists
     * @param animals    the Animals
     * @pre name != null && !name.isBlank() &&
     * scientist != null &&
     * animals != null
     * @post getName().equals(name) &&
     * getScientists().equals(scientists) &&
     * getAnimals().equals(animals) &&
     * getUsers().equals(users)
     */
    public Project(String name, List<Scientist> scientists, List<Animal> animals) {
        this(name, scientists, animals, new ArrayList<User>());
    }

    /**
     * Instantiates a new Project.
     *
     * @param name       the Name of the Project
     * @param scientists the Scientists
     * @param animals    the Animals
     * @param users      the Users
     * @pre name != null && !name.isBlank() &&
     * scientist != null &&
     * animals != null &&
     * users != null
     * @post getName().equals(name) &&
     * getScientists().equals(scientists) &&
     * getAnimals().equals(animals) &&
     * getUsers().equals(users)
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
     * Gets the List of Scientist.
     *
     * @return the List of Scientist
     */
    public List<Scientist> getScientists() {
        return this.scientists;
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
     * Adds a Scientist to the Project.
     *
     * @param scientist the Scientist
     * @pre scientist != null
     * @post scientists.size() = scientists.size()@prev + 1
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
     * @param scientist the Animal
     * @return true if scientist is in the List, false otherwise.
     * @pre none
     * @post none
     */
    public boolean containsScientist(Scientist scientist) {
        return this.scientists.contains(scientist);
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