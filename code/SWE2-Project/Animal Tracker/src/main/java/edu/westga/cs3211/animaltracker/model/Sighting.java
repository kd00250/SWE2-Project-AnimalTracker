package edu.westga.cs3211.animaltracker.model;

import java.time.LocalDateTime;

/**
 * Represents a documented sighting of an animal.
 *
 * @author mrocker1
 */
public class Sighting {
    private final int animalTagID;
    private final String location;
    private final double latitude;
    private final double longitude;
    private final LocalDateTime time;
    private final String notes;
    private final String username;

    /**
     * Instantiates a new Sighting object.
     *
     * @param animalID  the animal ID (mandatory)
     * @param location  the general location of the sighting (mandatory)
     * @param latitude  the latitude coordinate (mandatory)
     * @param longitude the longitude coordinate (mandatory)
     * @param time      the specific date and time of the sighting (mandatory)
     * @param notes     additional notes regarding the sighting (mandatory)
     * @param username  the username (optional), The reason it is optional is that
     *                  when creating the sighting locally, we do not store the
     *                  username and do not want to request the server to get it.
     */
    public Sighting(int animalID, String location, double latitude, double longitude, LocalDateTime time, String notes, String username) {
        if (animalID < 0) {
            throw new IllegalArgumentException("You must have an animal ID greater than zero");
        }
        if (isInvalidLocation(location)) {
            throw new IllegalArgumentException("Location must be provided.");
        }

        if (isInvalidLatitude(latitude)) {
            throw new IllegalArgumentException("Latitude must be a valid coordinate between -90 and 90.");
        }
        if (isInvalidLongitude(longitude)) {
            throw new IllegalArgumentException("Longitude must be a valid coordinate between -180 and 180.");
        }

        if (isInvalidTime(time)) {
            throw new IllegalArgumentException("Sighting time cannot be in the future.");
        }

        if (isInvalidNotes(notes)) {
            throw new IllegalArgumentException("Notes must contain valid text if provided.");
        }

        if (time == null) {
            throw new IllegalArgumentException("Sighting time cannot be null.");
        }

        if (notes == null) {
            throw new IllegalArgumentException("Notes cannot be null");
        }

        if (notes.isBlank()) {
            throw new IllegalArgumentException("Notes must contain valid text.");
        }

        this.animalTagID = animalID;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.notes = notes;
        this.username = username;
    }

    private static boolean isInvalidLocation(String location) {
        return location == null || location.trim().isEmpty();
    }

    private static boolean isInvalidNotes(String notes) {
        return notes != null && !notes.isEmpty() && notes.trim().isEmpty();
    }

    private static boolean isInvalidTime(LocalDateTime time) {
        return time != null && time.isAfter(LocalDateTime.now());
    }

    private static boolean isInvalidLongitude(double longitude) {
        return longitude < -180.0 || longitude > 180.0;
    }

    private static boolean isInvalidLatitude(double latitude) {
        return latitude < -90.0 || latitude > 90.0;
    }

    /**
     * Gets the animal.
     * @return the animal
     */
    public int getAnimalTagID() {
        return this.animalTagID;
    }

    /**
     * Gets the location.
     * @return the location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Gets the latitude coordinate.
     * @return the latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Gets the longitude coordinate.
     * @return the longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Gets the time of the sighting.
     * @return the time
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Gets the notes for the sighting.
     * @return the notes
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * Gets the Username.
     * @return Username
     */
    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return String.format("Sighting: %s at %s (%.5f, %.5f) with Notes: %s by %s at %s", this.animalTagID, this.location, this.latitude, this.longitude, this.notes, this.username, this.time);
    }
}