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
    requires org.junit.jupiter.api;

    exports viewone.graphical_controllers;
    opens viewone.graphical_controllers to javafx.fxml;
    exports viewone.graphical_controllers.athletes;
    opens viewone.graphical_controllers.athletes to javafx.fxml;
    exports viewone.graphical_controllers.trainers;
    opens viewone.graphical_controllers.trainers to javafx.fxml;
    exports viewone.graphical_controllers.launcher;
    opens viewone.graphical_controllers.launcher to javafx.fxml;
    exports viewone.beans_viewone;
    opens viewone.beans_viewone to javafx.fxml;
    exports engineering;
    opens engineering to javafx.fxml;
    exports viewone;
    opens viewone to javafx.fxml;

    exports viewtwo.graphical_controllers;
    opens viewtwo.graphical_controllers to javafx.fxml;
    exports viewtwo.graphical_controllers.launcher;
    opens viewtwo.graphical_controllers.launcher to javafx.fxml;
    exports viewtwo.graphical_controllers.athletes;
    opens viewtwo.graphical_controllers.athletes to javafx.fxml;
    exports viewtwo.graphical_controllers.trainers;
    opens viewtwo.graphical_controllers.trainers to javafx.fxml;
    exports viewtwo.beans_viewtwo;
    opens viewtwo.beans_viewtwo to javafx.fxml;
    exports viewtwo;
    opens viewtwo to javafx.fxml;
    exports database;
    opens database to javafx.fxml;

    exports exceptions;
    exports models;
    exports controllers;
    exports models.notification;
    exports engineering.manage_list;
    opens engineering.manage_list to javafx.fxml;
    exports exceptions.invalid_data_exception;
    exports models.record;
    exports exceptions.runtime_exception;
    exports database.queries;
    opens database.queries to javafx.fxml;
    exports beans;
    opens beans to javafx.fxml;
}