package edu.westga.cs3211.animaltracker.model.DataStorageModel;

import edu.westga.cs3211.animaltracker.model.DataStorage;
import edu.westga.cs3211.animaltracker.model.Role;
import edu.westga.cs3211.animaltracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGenerateToken {

    @BeforeEach
    void setUp() {
        DataStorage.reset();
    }

    @Test
    void testGenerateTokenForUser() {
        User user = new User("testUser", "password", Role.ADMIN);
        String token = DataStorage.generateTokenForUser(user);

        assertAll(() -> {
            assertNotNull(token);
            assertFalse(token.isBlank());
            assertEquals(user, DataStorage.getUserByToken(token));
            assertNotNull(DataStorage.getExpirationDateMap().get(token));
        });
    }

    @Test
    void testGenerateTokenForUserNull() {
        assertThrows(IllegalArgumentException.class, () -> DataStorage.generateTokenForUser(null));
    }
}
