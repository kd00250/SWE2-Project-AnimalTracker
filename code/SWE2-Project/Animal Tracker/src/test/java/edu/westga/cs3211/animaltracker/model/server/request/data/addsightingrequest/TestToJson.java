package edu.westga.cs3211.animaltracker.model.server.request.data.addsightingrequest;

import static edu.westga.cs3211.animaltracker.model.server.request.SeverSettings.ADD_SIGHTING_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import edu.westga.cs3211.animaltracker.model.server.request.data.AddSightingRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Sighting;

class TestToJson {

    @Test
    void toJsonShouldReturnCorrectJsonObject() {
        Animal animal = new Animal(AnimalClass.MAMMAL, 120.5, 75.2, 50.0, 1001, "Adult deer");
        LocalDateTime time = LocalDateTime.of(2026, 4, 10, 14, 30);
        Sighting sighting = new Sighting(
                animal.getTagID(),
                "Forest Area A",
                33.7490,
                -84.3880,
                time,
                "Seen near the trees");

        AddSightingRequest request = new AddSightingRequest("token123", sighting);

        JSONObject json = request.toJson();

        assertEquals(ADD_SIGHTING_REQUEST, json.getString("action"));
        assertEquals("token123", json.getString("token"));
        assertEquals(animal.getTagID(), json.get("animal"));
        assertEquals("Forest Area A", json.getString("location"));
        assertEquals(33.7490, json.getDouble("latitude"));
        assertEquals(-84.3880, json.getDouble("longitude"));
        assertEquals(time, json.get("time"));
        assertEquals("Seen near the trees", json.getString("notes"));
    }
}
