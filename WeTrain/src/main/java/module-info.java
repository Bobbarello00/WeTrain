module com.wetrain.wetrain {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.jetbrains.annotations;
    requires java.sql;
    requires mysql.connector;
    requires java.datatransfer;
    requires java.desktop;

    exports viewone.graphical_controllers;
    opens viewone.graphical_controllers to javafx.fxml;
    exports viewone.graphical_controllers.athletes;
    opens viewone.graphical_controllers.athletes to javafx.fxml;
    exports viewone.graphical_controllers.trainers;
    opens viewone.graphical_controllers.trainers to javafx.fxml;
    exports viewone.graphical_controllers.launcher;
    opens viewone.graphical_controllers.launcher to javafx.fxml;
    exports viewone.bean;
    opens viewone.bean to javafx.fxml;
    exports viewone.engeneering;
    opens viewone.engeneering to javafx.fxml;
    exports viewone;
    opens viewone to javafx.fxml;

    exports viewtwo.graphical_controllers1;
    opens viewtwo.graphical_controllers1 to javafx.fxml;
    exports viewtwo.graphical_controllers1.launcher1;
    opens viewtwo.graphical_controllers1.launcher1 to javafx.fxml;
    exports viewtwo;
    opens viewtwo to javafx.fxml;
    exports database;
    opens database to javafx.fxml;

    exports exception;
    exports model;
    exports controller;
    exports model.notification;
    exports viewone.engeneering.manageList;
    opens viewone.engeneering.manageList to javafx.fxml;
    exports exception.invalid_data_exception;
}