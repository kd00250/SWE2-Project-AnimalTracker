package edu.westga.cs3211.animaltracker.model.server.request.data.addprojectrequest;

import edu.westga.cs3211.animaltracker.model.server.request.InvalidRequestException;
import edu.westga.cs3211.animaltracker.model.server.request.data.AddProjectRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConstuctor {


    @Test
    void testValidRequestConstructed() {
        var name = "1234";
        var scientistUsernames = new ArrayList<String>();
        var animalIds = new ArrayList<Integer>();
        scientistUsernames.add("1234");
        scientistUsernames.add("5678");
        scientistUsernames.add("7890");
        animalIds.add(1);
        animalIds.add(2);
        animalIds.add(3);
        String token = "www";
        AddProjectRequest request = new AddProjectRequest(name, scientistUsernames, animalIds, token);

        assertEquals(request.getAnimalIds(), animalIds);
        assertEquals(request.getScientistUsernames(), scientistUsernames);
        assertEquals(name, request.getProjectName());

    }
}
