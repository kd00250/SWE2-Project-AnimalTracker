/**
 * The animal tracker module.
 */
module edu.westga.cs3211.animaltracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires jdk.jshell;

    opens edu.westga.cs3211.animaltracker.view to javafx.fxml;
    exports edu.westga.cs3211.animaltracker;
    opens edu.westga.cs3211.animaltracker.viewmodel to javafx.fxml;
}
