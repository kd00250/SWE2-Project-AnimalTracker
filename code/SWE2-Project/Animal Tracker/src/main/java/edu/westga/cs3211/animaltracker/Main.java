package edu.westga.cs3211.animaltracker;

import java.io.IOException;

import edu.westga.cs3211.animaltracker.model.client.Client;
import edu.westga.cs3211.animaltracker.view.swap.PageInformation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Main Application class.
 *
 * @author CS 3211
 * @version Fall 2025
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = this.loadGui();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle(PageInformation.LOGIN_TITLE);
            primaryStage.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private Pane loadGui() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource(PageInformation.LOGIN_PATH));
        return loader.load();
    }

    /**
     * Entry point for the application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
