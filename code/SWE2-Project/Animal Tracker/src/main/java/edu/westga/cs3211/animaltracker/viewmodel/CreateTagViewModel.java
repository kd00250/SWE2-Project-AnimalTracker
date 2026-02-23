package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.AnimalClass;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

import java.util.Random;

/**
 * The CreateTagViewModel class.
 */
public class CreateTagViewModel {
    private ObjectProperty<AnimalClass> animalClass;
    private StringProperty tagId;
    private StringProperty description;

    /**
     * Initialize a new instance of CreateTagViewModel.
     */
    public CreateTagViewModel() {
        this.buildProperties();
    }

    /**
     * Animal Class property.
     *
     * @return the object property.
     */
    public ObjectProperty<AnimalClass> animalClassProperty() {
        return this.animalClass;
    }

    /**
     * Tag Id Property
     *
     * @return the tag id property
     */
    public StringProperty tagIdProperty() {
        return this.tagId;
    }

    /**
     * Description property
     *
     * @return the description property
     */
    public StringProperty descriptionProperty() {
        return this.description;
    }

    /**
     * Generates a random six-digit id for tag creation
     */
    public void generateTagId() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        this.tagId.set(String.valueOf(randomNumber));
    }

    /**
     * Sets properties to default values.
     */
    public void clear() {
        this.animalClass.set(null);
        this.tagId.set("******");
        this.description.set("");
    }

    /**
     * Checks the state if a user can submit a tag.
     *
     * @return a true or false depending on if they can submit
     */
    public BooleanBinding canSubmit() {
        return this.description.isEmpty()
                .or(this.animalClass.isNull())
                .or(this.tagId.isEqualTo("******"));
    }

    private void buildProperties() {
        this.animalClass = new SimpleObjectProperty<>();
        this.tagId = new SimpleStringProperty("******");
        this.description = new SimpleStringProperty("");
    }
}
