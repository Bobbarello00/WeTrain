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
    exports com.wetrain.wetrain.controllers;
    opens com.wetrain.wetrain.controllers to javafx.fxml;
    exports com.wetrain.wetrain.controllers.athletes;
    opens com.wetrain.wetrain.controllers.athletes to javafx.fxml;
    exports com.wetrain.wetrain.controllers.nutritionists;
    opens com.wetrain.wetrain.controllers.nutritionists to javafx.fxml;
    exports com.wetrain.wetrain.controllers.trainers;
    opens com.wetrain.wetrain.controllers.trainers to javafx.fxml;
    exports com.wetrain.wetrain.controllers.launcher;
    opens com.wetrain.wetrain.controllers.launcher to javafx.fxml;
}