module com.uisil.proyecto_museo_dragones {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
     requires jakarta.mail;

    exports com.uisil.proyecto_museo_dragones;
    exports com.uisil.proyecto_museo_dragones.controllers;

    opens com.uisil.proyecto_museo_dragones.controllers to javafx.fxml;
}