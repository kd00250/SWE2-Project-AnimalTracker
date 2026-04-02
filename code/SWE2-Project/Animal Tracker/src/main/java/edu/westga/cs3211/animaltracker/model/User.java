package edu.westga.cs3211.animaltracker.model;

/**
 * The User Class.
 *
 * @author mrocker1
 */
public record User(String username, String password, Role role) {

    /**
     * Instantiates a new User.
     *
     * @param username the Username
     * @param password the password
     * @param role     the role
     * @pre username != null && !username.isBlank() &&
     * password != null && !password.isBlank()
     */
    public User(String username, String password, Role role) {
        if (username == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (username.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        if (password == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("ID cannot be blank");
        }
        this.username = username;
        this.password = password;
        this.role = role;
        DataStorage.getUsernameMap().put(username, this);
    }

    /**
     * Gets the User Name.
     *
     * @return the Scientist Name
     */
    @Override
    public String username() {
        return this.username;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    @Override
    public String password() {
        return this.password;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    @Override
    public Role role() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
