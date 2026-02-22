package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.login.request.LoginRequest;
import edu.westga.cs3211.animaltracker.model.login.request.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.AuthLoginService;
import edu.westga.cs3211.animaltracker.model.login.service.LocalLoginAuth;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private StringProperty username;
    private StringProperty password;
    private AuthLoginService authenticator;
    private SimpleObjectProperty<LoginResponse> loginResponse;

    public LoginViewModel() {
        this.username = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
        this.loginResponse = new SimpleObjectProperty<>();
        this.authenticator = new LocalLoginAuth();
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
        return this.authenticator.isValidToken(this.loginResponse.getValue().getToken());
    }
    public LoginResponse getLoginResponse() {
        return this.loginResponse.getValue();
    }
}
