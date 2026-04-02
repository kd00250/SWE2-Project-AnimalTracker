package edu.westga.cs3211.animaltracker.model.server.request.auth;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.LOGIN_REQUEST;

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

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return this.password;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", LOGIN_REQUEST);
        json.put("username", this.username);
        json.put("password", this.password);

        return json;
    }
}
