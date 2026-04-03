package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.ADD_USER_REQUEST;

/**
 * the add user request class.
 */
public final class AddUserRequest extends Request {
    private final String username;
    private final String password;
    private final Role role;

    /**
     * creates a new instance of add user request.
     * @param username the username
     * @param password the password
     * @param role the role
     */
    public AddUserRequest(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Validates the Request to see if the entries are null or not.
     */
    @Override
    public void validateRequest() {
        if (this.username == null) {
            throw new InvalidRequestException("Username is null");
        }
        if (this.password == null) {
            throw new InvalidRequestException("Password is null");
        }
        if (this.role == null) {
            throw new InvalidRequestException("Role is null");
        }
    }

    /**
     * json to be sent.
     * @return The json to be sent.
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("action", ADD_USER_REQUEST);
        json.put("username", this.username);
        json.put("password", this.password);
        String roleText = this.role.toString().toUpperCase();
        json.put("role", roleText);
        return json;
    }
}
