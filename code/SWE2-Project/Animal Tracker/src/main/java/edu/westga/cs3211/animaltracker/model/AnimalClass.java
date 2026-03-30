package edu.westga.cs3211.animaltracker.model;

/**
 * The enum animal class.
 */
public enum AnimalClass {
    /**
     * The mammal enum.
     */
    MAMMAL,
    /**
     * the bird enum.
     */
    BIRD,
    /**
     * the fish enum.
     */
    FISH,
    /**
     * the reptile enum.
     */
    REPTILE,
    /**
     * the amphibian enum.
     */
    AMPHIBIAN;

    @Override
    public String toString() {
        return switch (this) {
            case MAMMAL -> "Mammal";
            case BIRD -> "Bird";
            case FISH -> "Fish";
            case REPTILE -> "Reptile";
            case AMPHIBIAN -> "Amphibian";
        };
    }
}
