package edu.westga.cs3211.animaltracker.viewmodel.CreateTag;

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
    void testCancelButtonConfirmation() {
        this.viewModel.generateTagId();
        this.viewModel.animalClassProperty().set(AnimalClass.MAMMAL);
        this.viewModel.descriptionProperty().set("Description");
        this.viewModel.clear();
        assertNull(this.viewModel.animalClassProperty().get());
        assertEquals("******", this.viewModel.tagIdProperty().get());
        assertEquals("", this.viewModel.descriptionProperty().get());
    }

}
