package dam.davinci.tarea10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/***
 * Esta clase inicia la aplicación.
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*Aquí se carga el nodo raiz por medio de FXML. Este será el modelo de toda la aplicación para separar la
        * vista de la lógica.*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DAM_Prog10-11 - Gestión vehículos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}