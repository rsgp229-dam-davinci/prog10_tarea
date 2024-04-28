module dam.davinci.tarea10 {
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires javafx.base;

    opens dam.davinci.tarea10 to javafx.base, javafx.fxml;
    exports dam.davinci.tarea10;
    exports dam.davinci.tarea10.controllers;
    opens dam.davinci.tarea10.controllers to javafx.fxml, javafx.base;
    opens dam.davinci.tarea10.models to javafx.fxml, javafx.base;
}