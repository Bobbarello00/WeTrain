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


    exports viewone.graphical_controllers;
    opens viewone.graphical_controllers to javafx.fxml;
    exports viewone.graphical_controllers.athletes;
    opens viewone.graphical_controllers.athletes to javafx.fxml;
    exports viewone.graphical_controllers.trainers;
    opens viewone.graphical_controllers.trainers to javafx.fxml;
    exports viewone.graphical_controllers.launcher;
    opens viewone.graphical_controllers.launcher to javafx.fxml;
    exports viewone;
    opens viewone to javafx.fxml;
}