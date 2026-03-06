package edu.westga.cs3211.animaltracker.model;

/**
 * The Scientist Class.
 */
public class Scientist {

    private String name;
    private String id;

    /**
     * Instantiates a new Scientist.
     * @pre name != null && !name.isBlank() &&
     *      id != null && !id.isBlank()
     * @param name the Name
     * @param id the ID
     */
    public Scientist(String name, String id) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be blank");
        }
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the Scientist Name.
     * @return the Scientist Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the Scientist ID.
     * @return the Scientist ID
     */
    public String getId() {
        return this.id;
    }
}