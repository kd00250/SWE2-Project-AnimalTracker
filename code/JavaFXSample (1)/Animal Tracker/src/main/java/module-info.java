module edu.westga.cs3211.animaltracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;

    opens edu.westga.cs3211.animaltracker.view to javafx.fxml;
    exports edu.westga.cs3211.animaltracker;
}
