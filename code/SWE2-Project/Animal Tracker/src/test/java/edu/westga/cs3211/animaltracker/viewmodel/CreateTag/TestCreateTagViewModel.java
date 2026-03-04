package edu.westga.cs3211.animaltracker.viewmodel.CreateTag;

import edu.westga.cs3211.animaltracker.model.Animal;
import edu.westga.cs3211.animaltracker.model.AnimalClass;
import edu.westga.cs3211.animaltracker.viewmodel.CreateTagViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TestCreateTagViewModel.
 */
public class TestCreateTagViewModel {

    private CreateTagViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.viewModel = new CreateTagViewModel();
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
    void testCancelButtonConfirmation() {
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
    void testIdIsntInteger() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.lengthProperty().set("1.2");
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testLengthIsntDouble() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.generateTagId();
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testHeightIsntDouble() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.generateTagId();
        this.viewModel.lengthProperty().set("1.2");
        this.viewModel.weightProperty().set("1.2");
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

    @Test
    void testWeightIsntDouble() {
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.generateTagId();
        this.viewModel.heightProperty().set("1.2");
        this.viewModel.lengthProperty().set("1.2");
        assertFalse(this.viewModel.errorMessageProperty().get().isEmpty());
    }

//    @Test
//    void testMakeValidTag() {
//        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
//        this.viewModel.descriptionProperty().set("Description");
//        this.viewModel.tagIdProperty().set("123456");
//        this.viewModel.heightProperty().set("1.2");
//        this.viewModel.lengthProperty().set("1.2");
//        this.viewModel.weightProperty().set("1.2");
//        Animal rat = this.viewModel.makeTag();
//
//        int expectedId = 123456;
//        double expectedWeight = 1.2;
//        double expectedLength = 1.2;
//        double expectedHeight = 1.2;
//
//        assertNotNull(rat);
//        assertEquals(AnimalClass.MAMMAL, rat.getAnimalClass());
//        assertEquals("Description", rat.getDescription());
//        assertEquals(expectedId, rat.getTagID());
//        assertEquals(expectedWeight, rat.getWeight());
//        assertEquals(expectedLength, rat.getHeight());
//        assertEquals(expectedHeight, rat.getLength());
//
//    }

}
