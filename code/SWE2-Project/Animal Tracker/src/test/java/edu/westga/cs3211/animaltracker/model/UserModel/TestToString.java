package edu.westga.cs3211.animaltracker.model.UserModel;

import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestToString {

    @Test
    void validToString() {
        var user = new User("John Doe", "123456789", Role.ADMIN);
        assertEquals("John Doe", user.toString());
    }
}
