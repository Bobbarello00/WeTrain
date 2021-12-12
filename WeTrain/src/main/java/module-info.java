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
    exports com.wetrain.wetrain.Controllers;
    opens com.wetrain.wetrain.Controllers to javafx.fxml;
    exports com.wetrain.wetrain.Controllers.Athletes;
    opens com.wetrain.wetrain.Controllers.Athletes to javafx.fxml;
    exports com.wetrain.wetrain.Controllers.Nutritionist;
    opens com.wetrain.wetrain.Controllers.Nutritionist to javafx.fxml;
    exports com.wetrain.wetrain.Controllers.Trainers;
    opens com.wetrain.wetrain.Controllers.Trainers to javafx.fxml;
    exports com.wetrain.wetrain.Controllers.Launcher;
    opens com.wetrain.wetrain.Controllers.Launcher to javafx.fxml;
}