package edu.westga.cs3211.animaltracker.viewmodel;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.login.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.login.service.ServerService;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

import java.util.Random;

/**
 * The CreateTagViewModel class.
 */
public class CreateTagViewModel {
    private static final int MINIMUM_VALUE = 100000;
    private static final int MAXIMUM_VALUE = 900000;
    private static final String DEFAULT_TAG = "******";
    private ObjectProperty<AnimalClass> animalClass;
    private StringProperty tagId;
    private StringProperty description;
    private StringProperty height;
    private StringProperty length;
    private StringProperty weight;
    private StringProperty errorMessage;

    private LoginResponse authSession;
    private ServerService serverService;
    private ViewProjectDataViewModel viewProjectViewModel;

    /**
     * Initialize a new instance of CreateTagViewModel.
     */
    public CreateTagViewModel() {
        this.buildProperties();
    }

    public void setSession(LoginResponse session, ServerService server, ViewProjectDataViewModel viewProjectViewModel) {
        this.authSession = session;
        this.serverService = server;
        this.viewProjectViewModel = viewProjectViewModel;

    }

    /**
     * Gets the session information.
     *
     * @return the session
     */
    public LoginResponse getSession() {
        return this.authSession;
    }

    /**
     * Gets the server service.
     *
     * @return the server service
     */
    public ServerService getServerService() {
        return this.serverService;
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
     * Tag Id Property.
     *
     * @return the tag id property
     */
    public StringProperty tagIdProperty() {
        return this.tagId;
    }

    /**
     * Description property.
     *
     * @return the description property
     */
    public StringProperty descriptionProperty() {
        return this.description;
    }

    /**
     * Height property.
     *
     * @return the height property
     */
    public StringProperty heightProperty() {
        return this.height;
    }

    /**
     * Length property.
     *
     * @return the length property
     */
    public StringProperty lengthProperty() {
        return this.length;
    }

    /**
     * Weight property.
     *
     * @return the weight property
     */
    public StringProperty weightProperty() {
        return this.weight;
    }

    /**
     * Error message property.
     *
     * @return the error message property
     */
    public StringProperty errorMessageProperty() {
        return this.errorMessage;
    }

    /**
     * Generates a random six-digit id for tag creation.
     */
    public void generateTagId() {
        Random random = new Random();
        int randomNumber = MINIMUM_VALUE + random.nextInt(MAXIMUM_VALUE);
        this.tagId.set(String.valueOf(randomNumber));
    }

    /**
     * Sets properties to default values.
     */
    public void clear() {
        this.animalClass.set(null);
        this.tagId.set(DEFAULT_TAG);
        this.description.set("");
        this.height.set("");
        this.length.set("");
        this.weight.set("");
        this.errorMessage.set("");
    }

    /**
     * Makes an animal tag based off the input provided.
     * If tag is unable to made, a null value is returned.
     *
     *
     */
    public void makeTag() {
        try {
            double heightValue = this.parseDoubleOrThrow(this.height.get(), "Height");
            double weightValue = this.parseDoubleOrThrow(this.weight.get(), "Weight");
            double lengthValue = this.parseDoubleOrThrow(this.length.get(), "Length");
            int id = this.parseIntOrThrow(this.tagId.get());
            Animal animal = new Animal(this.animalClass.get(), heightValue, weightValue, lengthValue, id, this.description.get());
            this.viewProjectViewModel.getProjectProperty().get().addAnimal(animal);

        } catch (Exception e) {
            this.errorMessage.set(e.getMessage());
        }
    }

    /**
     * Checks the state if a user can submit a tag.
     *
     * @return a true or false depending on if they can submit
     */
    public BooleanBinding isSubmitInvalid() {
        return this.description.isEmpty()
                .or(this.animalClass.isNull())
                .or(this.tagId.isEqualTo(DEFAULT_TAG))
                .or(this.height.isEmpty())
                .or(this.length.isEmpty())
                .or(this.weight.isEmpty());
    }

    private double parseDoubleOrThrow(String value, String fieldName) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a number.");
        }
    }

    private int parseIntOrThrow(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID" + " must be a whole number.");
        }
    }

    private void buildProperties() {
        this.animalClass = new SimpleObjectProperty<>();
        this.tagId = new SimpleStringProperty(DEFAULT_TAG);
        this.description = new SimpleStringProperty("");
        this.height = new SimpleStringProperty("");
        this.length = new SimpleStringProperty("");
        this.weight = new SimpleStringProperty("");
        this.errorMessage = new SimpleStringProperty("");
    }
}
