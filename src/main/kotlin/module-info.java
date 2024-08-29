module to.grindelf.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.kotlin;
    requires com.fasterxml.jackson.databind;
    requires kotlin.stdlib;
    requires java.sql;
    requires java.desktop;


    opens to.grindelf.todoapp to javafx.fxml;
    exports to.grindelf.todoapp;
    opens to.grindelf.todoapp.controllers to javafx.fxml;
    exports to.grindelf.todoapp.controllers;
    opens to.grindelf.todoapp.utils to javafx.fxml;
    exports to.grindelf.todoapp.utils;
}
