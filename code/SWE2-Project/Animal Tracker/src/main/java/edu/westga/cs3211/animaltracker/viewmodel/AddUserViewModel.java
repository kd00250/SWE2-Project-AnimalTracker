package edu.westga.cs3211.animaltracker.viewmodel;

import com.sun.nio.sctp.IllegalReceiveException;
import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidResponseException;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddUserRequest;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * the add user view model.
 */
public class AddUserViewModel {
    private LoginResponse authSession;
    private ServerService serverService;
    private final StringProperty username;
    private final StringProperty password;
    private final ObjectProperty<Role> role;

    /**
     * Initializes a new instance of add user view model.
     */
    public AddUserViewModel() {
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.role = new SimpleObjectProperty<>();
    }

    /**
     * Sets the session for this view model.
     *
     * @param session the user's session
     * @param server the server service
     */
    public void setSession(LoginResponse session, ServerService server) {
        this.authSession = session;
        this.serverService = server;
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

    /**
     * Gets the session information.
     *
     * @return the session
     */
    public LoginResponse getSession() {
        return this.authSession;
    }

    /**
     * Gets the server service.
     *
     * @return the server service
     */
    public ServerService getServerService() {
        return this.serverService;
    }

    private boolean isUsernameValid() {
        return DataStorage.isUsernameAvailable(this.username.getValue());
    }

    /**
     * Creates and adds a new user to the system.
     */
    public void createNewUser() {
//        if (!this.isUsernameValid()) {
//            throw new IllegalArgumentException("Username is already taken, please try again");
//        }
//        String username = this.getUsername().get();
//        String password = this.getPassword().get();
//        Role role = this.getRole().get();
//        User user = new User(username, password, role);
//        DataStorage.getUsers().add(user);
        AddUserRequest request = new AddUserRequest(this.getUsername().get(), this.getPassword().get(), this.getRole().get());
        boolean validResponse = this.serverService.addUser(request);
        if (!validResponse) {
            throw new InvalidResponseException("Username is already in use");
        }
    }
}
