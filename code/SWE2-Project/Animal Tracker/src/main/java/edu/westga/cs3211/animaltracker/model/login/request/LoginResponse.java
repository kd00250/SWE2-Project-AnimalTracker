package edu.westga.cs3211.animaltracker.model.login.request;

/**
 * The login response class.
 */
public final class LoginResponse extends Response {

    private final String loginToken;
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
}
