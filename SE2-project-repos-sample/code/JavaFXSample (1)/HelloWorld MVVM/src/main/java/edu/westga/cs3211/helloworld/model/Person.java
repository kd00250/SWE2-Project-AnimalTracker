package edu.westga.cs3211.helloworld.model;

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
	 * @precondition name!=null 
	 * @postcondition getName() == name 
	 * 
	 * @param name
	 *            name of the person
	 */
	public Person(String name) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}

		this.name = name;
	}
	
	/**
	 * Returns the Person's name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Person's name
	 */
	public String getName() {
		return this.name;
	}
}
