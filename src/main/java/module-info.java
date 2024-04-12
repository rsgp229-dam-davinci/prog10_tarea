module dam.davinci.tarea10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens dam.davinci.tarea10 to javafx.fxml;
    exports dam.davinci.tarea10;
}