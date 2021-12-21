module com.wetrain.wetrain {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;

    opens com.wetrain.wetrain to javafx.fxml;
    exports com.wetrain.wetrain;
    exports com.wetrain.wetrain.graphical_controllers;
    opens com.wetrain.wetrain.graphical_controllers to javafx.fxml;
    exports com.wetrain.wetrain.graphical_controllers.athletes;
    opens com.wetrain.wetrain.graphical_controllers.athletes to javafx.fxml;
    exports com.wetrain.wetrain.graphical_controllers.nutritionists;
    opens com.wetrain.wetrain.graphical_controllers.nutritionists to javafx.fxml;
    exports com.wetrain.wetrain.graphical_controllers.trainers;
    opens com.wetrain.wetrain.graphical_controllers.trainers to javafx.fxml;
    exports com.wetrain.wetrain.graphical_controllers.launcher;
    opens com.wetrain.wetrain.graphical_controllers.launcher to javafx.fxml;
}