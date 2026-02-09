package edu.westga.cs3211.helloworld.viewmodel;

import edu.westga.cs3211.helloworld.model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class ViewModel.
 * 
 * @author CS 3211
 * @version Fall 2025
 */

public class GreetingViewModel {

	private StringProperty greetingProperty;
	private StringProperty nameProperty;
	private StringProperty allNamesProperty;

	/**
	 * Instantiates a new greeting view model.
	 */
	public GreetingViewModel() {
		// this will set the CodeBehind's greetingLabel to Hello, World!
		// because
		// this.greetingLabel.textProperty().bind(this.viewModel.greetingProperty());
		// i.e., the CodeBehind's greetingLabel.textProperty is
		// unidirectionally bound to greetingProperty
		// when the greetingProperty's text changes, it triggers the change
		// in the CodeBehind's greetingLabel
		this.greetingProperty = new SimpleStringProperty("Hello, World!");
		this.nameProperty = new SimpleStringProperty("");
		this.allNamesProperty = new SimpleStringProperty("");
	}

	/**
	 * Gets the greeting property.
	 *
	 * @return the greeting Property.
	 */
	public StringProperty greetingProperty() {
		return this.greetingProperty;
	}

	/**
	 * Gets the name property.
	 *
	 * @return the nameProperty
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Gets the allNamesProperty.
	 *
	 * @return the allNamesProperty
	 */
	public StringProperty allNamesProperty() {
		return this.allNamesProperty;
	}
	/**
	 * Say greetings.
	 * 
	 * @precondition: none
	 * @postcondition: the greeting label has text "Hello, xxx!" where xxx is what
	 *                 the user entered in the nameTextfield and nameTextField is
	 *                 set to empty. If the user didn't enter any name, returns.
	 * 
	 */

	public void sayGreeting() {

		String name = this.nameProperty.getValue();
		if (!name.isEmpty()) {
			Person person = new Person(name);
			this.greetingProperty.setValue("Hello! " + person.getName());

			if (this.allNamesProperty.getValue().isEmpty()) {
				this.allNamesProperty.setValue(name);
			} else {
				this.allNamesProperty.setValue(this.allNamesProperty.getValue() 
						+ System.lineSeparator() + name);
			}
			// this sets the viewModel's nameProperty to empty
			// since it's bi-directionally bound to the codebehind's
			// nameTextField.textProperty
			// it will set nameTextField to empty.
			this.nameProperty.setValue("");
		}

	}

}
