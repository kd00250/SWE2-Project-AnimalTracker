package edu.westga.cs3211.animaltracker.model;

/**
 * Defines a person
 *
 * @author CS 3211
 * @version Fall 2025
 */
public class Person {

    private static final String NAME_CANNOT_BE_NULL = "name cannot be null.";
    private String name;

    /**
     * Creates a new Person with the specified name.
     *
     * @param name name of the person
     * @pre name!=null
     * @pos getName() == name
     */
    public Person(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
        }

        this.name = name;
    }

    /**
     * Returns the Person's name.
     *
     * @return the Person's name
     * @pre none
     * @pos none
     */
    public String getName() {
        return this.name;
    }
}
