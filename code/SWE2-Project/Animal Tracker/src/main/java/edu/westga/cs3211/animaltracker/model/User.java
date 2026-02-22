package edu.westga.cs3211.animaltracker.model;

/**
 * The Scientist Class.
 * @author mrocker1
 */
public class User {

    private String name;
    private String id;
    private Role role;

    /**
     * Instantiates a new Scientist.
     * @pre name != null && !name.isBlank() &&
     *      id != null && !id.isBlank()
     * @param name the Name
     * @param id the ID
     */
    public User(String name, String id, Role role) {
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
        this.role = role;
    }

    /**
     * Gets the Scientist Name.
     * @return the Scientist Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the user ID.
     * @return the user ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the role.
     * @return the role
     */
    public Role getRole() { return this.role;}
}
