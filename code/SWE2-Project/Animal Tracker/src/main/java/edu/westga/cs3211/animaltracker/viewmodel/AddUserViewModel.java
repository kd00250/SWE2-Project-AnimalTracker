package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * the add user view model.
 */
public class AddUserViewModel {
    private StringProperty username;
    private StringProperty password;
    private ObjectProperty<Role> role;

    /**
     * Initializes a new instance of add user view model.
     */
    public AddUserViewModel() {
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.role = new SimpleObjectProperty<>();
    }

    /**
     * gets the username property.
     *
     * @return the username property
     */
    public StringProperty getUsername() {
        return this.username;
    }

    /**
     * gets the password property.
     *
     * @return the password property
     */
    public StringProperty getPassword() {
        return this.password;
    }

    /**
     * gets the role property.
     *
     * @return the role property
     */
    public ObjectProperty<Role> getRole() {
        return this.role;
    }

    private boolean isUsernameValid() {
        return DataStorage.isUsernameAvailable(this.username.getValue());
    }

    /**
     * Creates and adds a new user to the system.
     */
    public void createNewUser() {
        if (!this.isUsernameValid()) {
            throw new IllegalArgumentException("Username is already taken, please try again");
        }
        String username = this.getUsername().get();
        String password = this.getPassword().get();
        Role role = this.getRole().get();
        User user = new User(username, password, role);
        DataStorage.getUsers().add(user);
    }
}
