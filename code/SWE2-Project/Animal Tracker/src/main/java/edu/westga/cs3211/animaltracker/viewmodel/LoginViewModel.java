package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.RemoteServer;
import edu.westga.cs3211.animaltracker.model.server.service.ServerService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The login view model class.
 */
public class LoginViewModel {
    private final StringProperty username;
    private final StringProperty password;
    private ServerService authenticator;
    private final SimpleObjectProperty<LoginResponse> loginResponse;

    /**
     * Instantiates a new login view model.
     */
    public LoginViewModel() {
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.loginResponse = new SimpleObjectProperty<>();
        //this.authenticator = new LocalServer();
        this.authenticator = new RemoteServer();
    }

    /**
     * Sets the server instance, primarily used for testing purposes.
     * @param server the server
     */
    public void setServer(ServerService server) {

        if (server == null) {
            throw new IllegalArgumentException("server cannot be null");
        }

        this.authenticator = server;
    }

    /**
     * Gets the username property.
     * @return the username property
     */
    public StringProperty usernameProperty() {
        return this.username;
    }

    /**
     * Gets the password property.
     * @return the password property
     */
    public StringProperty passwordProperty() {
        return this.password;
    }

    /**
     * Request that the current information in the filled out information should be used to login.
     */
    public void processLoginRequest() {
        var response = this.authenticator.login(new LoginRequest(this.usernameProperty().get(), this.passwordProperty().get()));
        this.loginResponse.setValue(response);
    }

    /**
     * Checks if the login request was valid.
     * @return true if valid, otherwise false
     */
    public boolean isLoginValid() {
        return this.loginResponse.getValue() != null;
    }

    /**
     * Gets the login response value.
     * @return the login response
     */
    public LoginResponse getLoginResponse() {
        return this.loginResponse.getValue();
    }

    /**
     * Gets the server.
     * @return the server
     */
    public ServerService getServerService() {
        return this.authenticator;
    }
}
