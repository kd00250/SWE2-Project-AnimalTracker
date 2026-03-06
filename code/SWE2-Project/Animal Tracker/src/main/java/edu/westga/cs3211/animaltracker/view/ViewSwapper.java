package edu.westga.cs3211.animaltracker.view;

import edu.westga.cs3211.animaltracker.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The view swapper class.
 */
public final class ViewSwapper {

    /**
     * Loads a page from a given fxmlPath from the node given with a set title.
     * @param <T> the controller type
     * @param fxmlPath the fxml path
     * @param node the node
     * @param title the page title
     * @return the controller of the fxml page that was loaded.
     * @throws IOException if path cannot be found
     */
    public static <T> T loadPageFromStage(String fxmlPath, Node node, String title) throws IOException {
        if (fxmlPath == null) {
            throw new IllegalArgumentException("FXML path must be non null");
        }

        if (fxmlPath.isEmpty()) {
            throw new IllegalArgumentException("fxmlpath cannot be empty");
        }

        if (node == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }

        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Parent root = loader.load();
        T controller = loader.getController();

        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle(title);
        stage.show();

        return controller;
    }
}