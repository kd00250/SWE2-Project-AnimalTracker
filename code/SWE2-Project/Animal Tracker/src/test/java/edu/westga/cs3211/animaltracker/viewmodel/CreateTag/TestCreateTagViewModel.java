package edu.westga.cs3211.animaltracker.viewmodel.CreateTag;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.auth.LoginResponse;
import edu.westga.cs3211.animaltracker.model.server.service.LocalServer;
import edu.westga.cs3211.animaltracker.viewmodel.CreateTagViewModel;
import edu.westga.cs3211.animaltracker.viewmodel.ViewProjectDataViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TestCreateTagViewModel.
 */
public class TestCreateTagViewModel {

    private CreateTagViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.viewModel = new CreateTagViewModel();
        ViewProjectDataViewModel viewProjectViewModel = new ViewProjectDataViewModel();

        Project project = new Project(new ArrayList<>(),  "test", new ArrayList<>());
        viewProjectViewModel.setProject(project);

        LoginResponse session = new LoginResponse("validToken", 100);
        LocalServer localServer = new LocalServer();
        this.viewModel.setSession(session, localServer, viewProjectViewModel);
    }

    @Test
    void testConstructorInitializesProperties() {
        assertEquals("******", this.viewModel.tagIdProperty().get());
        assertEquals("", this.viewModel.descriptionProperty().get());
        assertEquals("", this.viewModel.weightProperty().get());
        assertEquals("", this.viewModel.lengthProperty().get());
        assertEquals("", this.viewModel.heightProperty().get());
        assertEquals("", this.viewModel.errorMessageProperty().get());
        assertNull(this.viewModel.animalClassProperty().get());
    }

    @Test
    void testSessionInitialization() {
        assertNotNull(this.viewModel.getSession());
        assertNotNull(this.viewModel.getServerService());
    }

    @Test
    void testSubmitButtonStatusIfDescriptionIsEmpty() {
        this.viewModel.generateTagId();
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        assertEquals("", this.viewModel.descriptionProperty().get());
        assertTrue(this.viewModel.isSubmitInvalid().get());
    }

    @Test
    void testSubmitButtonStatusIfTagIdIsntGenerated() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        assertEquals("******", this.viewModel.tagIdProperty().get());
        assertTrue(this.viewModel.isSubmitInvalid().get());
    }

    @Test
    void testSubmitButtonIfAnimalClassIsNull() {
        this.viewModel.generateTagId();
        this.viewModel.descriptionProperty().set("Description");
        assertNull(this.viewModel.animalClassProperty().get());
        assertTrue(this.viewModel.isSubmitInvalid().get());

    }

    @Test
    void testSubmitButtonIfWeightIsEmpty() {
        this.viewModel.generateTagId();
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.lengthProperty().set("1");
        this.viewModel.heightProperty().set("1");
        assertEquals("", this.viewModel.weightProperty().get());
        assertTrue(this.viewModel.isSubmitInvalid().get());

    }

    @Test
    void testSubmitButtonIfLengthIsEmpty() {
        this.viewModel.generateTagId();
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.weightProperty().set("1");
        this.viewModel.heightProperty().set("1");
        assertEquals("", this.viewModel.lengthProperty().get());
        assertTrue(this.viewModel.isSubmitInvalid().get());

    }

    @Test
    void testSubmitButtonIfHeightIsEmpty() {
        this.viewModel.generateTagId();
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.weightProperty().set("1");
        this.viewModel.lengthProperty().set("1");
        assertEquals("", this.viewModel.heightProperty().get());
        assertTrue(this.viewModel.isSubmitInvalid().get());

    }

    @Test
    void testClearButtonConfirmation() {
        this.viewModel.generateTagId();
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.clear();
        assertNull(this.viewModel.animalClassProperty().get());
        assertEquals("******", this.viewModel.tagIdProperty().get());
        assertEquals("", this.viewModel.descriptionProperty().get());
        assertEquals("", this.viewModel.heightProperty().get());
        assertEquals("", this.viewModel.weightProperty().get());
        assertEquals("", this.viewModel.lengthProperty().get());
        assertEquals("", this.viewModel.errorMessageProperty().get());
    }

    @Test
    void testIdIsntValid() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.lengthProperty().set("1.2");
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        boolean hasMade = this.viewModel.makeTag();

        assertFalse(hasMade);
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testLengthIsntValid() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.generateTagId();
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        this.viewModel.lengthProperty().set("ABC");

        boolean hasMade = this.viewModel.makeTag();

        assertFalse(hasMade);
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testHeightIsntValid() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.generateTagId();
        this.viewModel.lengthProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        this.viewModel.heightProperty().set("ABC");

        boolean hasMade = this.viewModel.makeTag();

        assertFalse(hasMade);
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testWeightIsntValid() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.generateTagId();
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.lengthProperty().set("1.2");
        this.viewModel.weightProperty().set("ABC");

        boolean hasMade = this.viewModel.makeTag();

        assertFalse(hasMade);
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testMakeValidTag() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.tagIdProperty().set("123456");
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.lengthProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        boolean hasMade = this.viewModel.makeTag();

        int expectedId = 123456;
        double expectedWeight = 1.2;
        double expectedLength = 1.2;
        double expectedHeight = 1.2;

        Project project = this.viewModel.getViewProjectViewModel().getProjectProperty().get();
        Animal newAnimal = project.getAnimals().getLast();

        assertTrue(hasMade);
        assertEquals(AnimalClass.MAMMAL, newAnimal.getAnimalClass());
        assertEquals("Description", newAnimal.getDescription());
        assertEquals(expectedId, newAnimal.getTagID());
        assertEquals(expectedWeight, newAnimal.getWeight());
        assertEquals(expectedLength, newAnimal.getHeight());
        assertEquals(expectedHeight, newAnimal.getLength());

    }

    @Test
    void testResetTagID() {
        this.viewModel.tagIdProperty().set("123456");
        this.viewModel.resetTagID();
        assertEquals("******", this.viewModel.tagIdProperty().get());
    }

}
