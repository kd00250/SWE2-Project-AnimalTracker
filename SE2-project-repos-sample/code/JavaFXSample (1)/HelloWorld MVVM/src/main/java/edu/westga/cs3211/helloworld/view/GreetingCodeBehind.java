package edu.westga.cs3211.helloworld.view;

import edu.westga.cs3211.helloworld.viewmodel.GreetingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The Class CodeBehind.
 * 
 * @author CS 3211
 * @version Fall 2025
 */
public class GreetingCodeBehind {

	@FXML
	private TextField nameTextField;

	@FXML
	private Button submitButton;

	@FXML
	private Label greetingLabel;
	
	@FXML
    private TextArea namesTextArea;

	private GreetingViewModel viewModel;

	/**
	 * Instantiates a new greeting code behind.
	 * 
	 * @precondition none
	 * @precondition none
	 */
	public GreetingCodeBehind() {
		this.viewModel = new GreetingViewModel();
	}

	@FXML
	void initialize() {

		this.bindComponentsToViewModel();
	}

	private void bindComponentsToViewModel() {
		this.greetingLabel.textProperty().bind(this.viewModel.greetingProperty());
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());
		this.namesTextArea.textProperty().bind(this.viewModel.allNamesProperty());
	}

	@FXML
	void handleSubmit() {

		this.viewModel.sayGreeting();

	}

}
