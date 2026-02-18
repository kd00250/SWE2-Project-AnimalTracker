package edu.westga.cs3211.animaltracker.model.login.request;

public final class LoginResponse extends Response {

    private String loginToken;
    private int timeout;

    public LoginResponse(String loginToken, int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout cannot be negative");
        }
        if (loginToken != null) {
            if (loginToken.isEmpty()) {
                throw new IllegalArgumentException("loginToken cannot be empty");
            }
        }

        this.loginToken = loginToken;
        this.timeout = timeout;
    }

    public String getToken() {
        return loginToken;
    }
}
