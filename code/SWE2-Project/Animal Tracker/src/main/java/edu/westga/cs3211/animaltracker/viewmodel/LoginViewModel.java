package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import edu.westga.cs3211.animaltracker.model.login.service.LocalServer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private StringProperty username;
    private StringProperty password;
    private ServerService authenticator;
    private SimpleObjectProperty<LoginResponse> loginResponse;

    public LoginViewModel() {
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.loginResponse = new SimpleObjectProperty<>();
        this.authenticator = new LocalServer();
    }

    public StringProperty usernameProperty() {
        return this.username;
    }

    public StringProperty passwordProperty() {
        return this.password;
    }

    public void processLoginRequest() {
        var response = this.authenticator.login(new LoginRequest(this.usernameProperty().get(), this.passwordProperty().get()));
        this.loginResponse.setValue(response);
    }

    public boolean isLoginValid() {
        if (this.loginResponse.getValue() == null) {
            return false;
        }
        return this.authenticator.isValidToken(this.loginResponse.getValue().getToken());
    }

    public LoginResponse getLoginResponse() {
        return this.loginResponse.getValue();
    }

    public ServerService getServerService() {
        return this.authenticator;
    }
}
