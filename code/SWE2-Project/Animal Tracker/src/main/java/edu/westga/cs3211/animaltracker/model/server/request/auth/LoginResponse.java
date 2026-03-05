package edu.westga.cs3211.animaltracker.model.server.request.auth;

import edu.westga.cs3211.animaltracker.model.server.request.Response;

import java.time.ZonedDateTime;

/**
 * The login response class.
 */
public final class LoginResponse extends Response {

    private final String loginToken;
    private ZonedDateTime creationTime;
    private final int timeout;

    /**
     * Initializes a new login response.
     *
     * @param loginToken the login token
     * @param timeout    the timeout until token is invalid
     */
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
        this.creationTime = ZonedDateTime.now();
    }

    /**
     * Instantiates a new login response.
     * @param loginToken the login token
     * @param timeout the users timeout
     * @param creationTime the creation time of this request
     */
    public LoginResponse(String loginToken, int timeout, ZonedDateTime creationTime) {
        this(loginToken, timeout);
        if (creationTime == null) {
            throw new IllegalArgumentException("creationTime cannot be null");
        }
        this.creationTime = creationTime;
    }

    /**
     * Gets the response token.
     *
     * @return the token
     */
    public String getToken() {
        return this.loginToken;
    }

    /**
     * Gets the timeout of this response.
     *
     * @return the time the token is valid for
     */
    public int getTimeout() {
        return this.timeout;
    }

    /**
     * Gets the creation time for this response.
     * @return the expiration time
     */
    public ZonedDateTime getCreationTime() {
        return this.creationTime;
    }
}
