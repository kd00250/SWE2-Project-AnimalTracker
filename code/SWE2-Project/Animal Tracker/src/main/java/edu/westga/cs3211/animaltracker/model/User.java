package edu.westga.cs3211.animaltracker.model;

/**
 * The User Class.
 *
 * @author mrocker1
 */
public class User {

    private String username;
    private String password;
    private Role role;

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
     * Gets the Username.
     *
     * @return the Scientist Name
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Role getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
