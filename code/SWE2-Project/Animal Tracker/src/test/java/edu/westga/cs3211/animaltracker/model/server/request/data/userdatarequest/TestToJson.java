package edu.westga.cs3211.animaltracker.model.server.request.data.userdatarequest;

import edu.westga.cs3211.animaltracker.model.server.request.data.UserDataRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.USER_ROLE_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToJson {

    @Test
    void shouldReturnCorrectJsonWithValidToken() {
        String token = "abc123";
        UserDataRequest request = new UserDataRequest(token);

        JSONObject json = request.toJson();

        assertEquals(USER_ROLE_REQUEST, json.getString("action"));
        assertEquals(token, json.getString("token"));
    }

    @Test
    void shouldReturnJsonWithEmptyTokenIfTokenIsEmpty() {
        String token = "";
        UserDataRequest request = new UserDataRequest(token);

        JSONObject json = request.toJson();

        assertEquals(USER_ROLE_REQUEST, json.getString("action"));
        assertEquals(token, json.getString("token"));
    }
}
