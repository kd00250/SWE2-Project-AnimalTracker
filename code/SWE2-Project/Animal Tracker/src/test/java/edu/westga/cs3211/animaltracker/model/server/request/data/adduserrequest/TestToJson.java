package edu.westga.cs3211.animaltracker.model.server.request.data.adduserrequest;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddUserRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.ADD_USER_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class TestToJson {

    @Test
    void testToJsonWithValidValuesReturnsCorrectJson() {
        AddUserRequest request = new AddUserRequest("kenny", "1234", Role.SCIENTIST);

        JSONObject json = request.toJson();

        assertEquals(ADD_USER_REQUEST, json.get("action"));
        assertEquals("kenny", json.getString("username"));
        assertEquals("1234", json.getString("password"));
        assertEquals("SCIENTIST", json.getString("role"));
    }

    @Test
    void testToJsonStoresRoleAsUppercaseString() {
        AddUserRequest request = new AddUserRequest("user1", "pass1", Role.ADMIN);

        JSONObject json = request.toJson();

        assertEquals(Role.ADMIN.toString().toUpperCase(), json.getString("role"));
    }
}
