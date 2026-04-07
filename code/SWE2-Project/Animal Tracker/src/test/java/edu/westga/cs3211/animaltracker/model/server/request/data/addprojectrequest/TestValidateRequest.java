package edu.westga.cs3211.animaltracker.model.server.request.data.addprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateRequest {
    @Test
    void testValidateThrowsNullName() {
        String name = null;
        var scientistUsernames = new ArrayList<String>();
        var animalIds = new ArrayList<Integer>();
        scientistUsernames.add("1234");
        scientistUsernames.add("5678");
        scientistUsernames.add("7890");
        animalIds.add(1);
        animalIds.add(2);
        animalIds.add(3);
        String token = "aaa";
        AddProjectRequest request = new AddProjectRequest(name, scientistUsernames, animalIds, token);
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
    @Test
    void testValidateThrowsEmptyName() {
        var name = "";
        var scientistUsernames = new ArrayList<String>();
        var animalIds = new ArrayList<Integer>();
        scientistUsernames.add("1234");
        scientistUsernames.add("5678");
        scientistUsernames.add("7890");
        animalIds.add(1);
        animalIds.add(2);
        animalIds.add(3);
        String token = "aaa";
        AddProjectRequest request = new AddProjectRequest(name, scientistUsernames, animalIds, token);
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
    @Test
    void testValidateThrowsNullScientistNames() {
        var name = "1234";
        ArrayList<String> scientistUsernames = null;
        var animalIds = new ArrayList<Integer>();
        animalIds.add(1);
        animalIds.add(2);
        animalIds.add(3);
        String token = "aaa";
        AddProjectRequest request = new AddProjectRequest(name, scientistUsernames, animalIds, token);
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }
    @Test
    void testValidateThrowsNullAnimalIds() {
        var name = "1234";
        var scientistUsernames = new ArrayList<String>();
        ArrayList<Integer> animalIds = null;
        scientistUsernames.add("1234");
        scientistUsernames.add("5678");
        scientistUsernames.add("7890");
        String token = "aaa";
        AddProjectRequest request = new AddProjectRequest(name, scientistUsernames, animalIds, token);
        assertThrows(InvalidRequestException.class, request::validateRequest);
    }

    @Test
    void testValidationIsValid() {
        var name = "1234";
        var scientistUsernames = new ArrayList<String>();
        ArrayList<Integer> animalIds = new ArrayList<Integer>();
        scientistUsernames.add("1234");
        scientistUsernames.add("5678");
        scientistUsernames.add("7890");
        String token = "aaa";
        AddProjectRequest request = new AddProjectRequest(name, scientistUsernames, animalIds, token);
        assertDoesNotThrow(request::validateRequest);
    }

}
