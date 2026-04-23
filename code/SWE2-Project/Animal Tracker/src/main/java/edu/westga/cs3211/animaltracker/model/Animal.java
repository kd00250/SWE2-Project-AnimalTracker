package edu.westga.cs3211.animaltracker.model;

/**
 * the animal class.
 */
public class Animal {
    private final AnimalClass animalClass;
    private final double height;
    private final double weight;
    private final double length;
    private final int tagID;
    private final String description;
    private final TagStatus tagStatus;
    private final int id;

    /**
     * instantiates a new instance of the animal class.
     *
     * @param animalClass the class of the animal
     * @param height      the height of the animal
     * @param weight      the weight of the animal
     * @param length      the length of the animal
     * @param tagID       the tagID of the animal
     * @param description the description of the animal
     */
    public Animal(AnimalClass animalClass, double height, double weight, double length, int tagID, String description) {
        if (animalClass == null) {
            throw new IllegalArgumentException("Class cannot be null");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height cannot be less than or equal to 0");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("weight cannot be less than or equal to 0");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be less than or equal to 0");
        }
        if (tagID <= 0) {
            throw new IllegalArgumentException("Length cannot be less than or equal to 0");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        this.animalClass = animalClass;
        this.height = height;
        this.weight = weight;
        this.length = length;
        this.tagID = tagID;
        this.tagStatus = TagStatus.ACTIVE;
        this.description = description;
        this.id = DataStorage.getNextAnimalId();
        DataStorage.getAnimals().put(this.getId(), this);
    }

    /**
     * gets the class of the animal.
     *
     * @return the animal class
     */
    public AnimalClass getAnimalClass() {
        return this.animalClass;
    }

    /**
     * gets the height of the animal.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * gets the weight of the animal.
     *
     * @return the weight
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * gets the length of the animal.
     *
     * @return the length
     */
    public double getLength() {
        return this.length;
    }

    /**
     * gets the tagID for the animal.
     *
     * @return the tagID
     */
    public int getTagID() {
        return this.tagID;
    }

    /**
     * Gets the tagStatus of the animal.
     *
     * @return the TagStatus
     */
    public TagStatus getTagStatus() {
        return this.tagStatus;
    }

    /**
     * gets the description of the animal description.
     *
     * @return the description of the animal
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * gets the animal id.
     * @return the animal id
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.getAnimalClass() + " " + this.getTagID();
    }
}
