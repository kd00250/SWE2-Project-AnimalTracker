package edu.westga.cs3211.animaltracker.model.login.request;

public final class LoginRequest extends Request {
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void validateRequest() {
        if (username == null || password == null) {
            throw new InvalidRequest("Username and/or password are mandatory");
        }
        if (username.isBlank() || password.isBlank()) {
            throw new InvalidRequest("Username and/or password are mandatory");
        }
    }
}
