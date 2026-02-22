package edu.westga.cs3211.animaltracker.model.login.request;

/**
 * The login request class.
 */
public final class LoginRequest extends Request {
    private final String username;
    private final String password;

    /**
     * Initializes a new login request with username and password.
     *
     * @param username the username
     * @param password the password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void validateRequest() {
        if (this.username == null) {
            throw new InvalidRequestException("Username is mandatory");
        }

        if (this.password == null) {
            throw new InvalidRequestException("Password are mandatory");
        }
        if (this.username.isEmpty()) {
            throw new InvalidRequestException("Password is mandatory");
        }

        if (this.password.isEmpty()) {
            throw new InvalidRequestException("Password are mandatory");
        }
    }
}
