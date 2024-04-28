package dam.davinci.tarea10.controllers;

import dam.davinci.tarea10.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/***
 * Clase controladora de la vista principal (main-scene.fxml)
 *
 * Esta es la clase que controla el flujo de ejecución del programa, llamando a las diferentes fucionalidades seleccionadas
 * según las opciones que ofrece el menú.
 */
public class MainSceneController {

    //Esta variable se utiliza para guardar la selección activa en la tabla
    private Vehicle selectedVehicle = null;
    public Button buttonModifyVehicle;
    public Button buttonDeleteVehicle;
    private DbFactory database;
    private PersonDAO personDAO;
    private VehicleDAO vehicleDAO;
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> columnBrand;
    @FXML
    private TableColumn<Vehicle, String> columnModel;
    @FXML
    private TableColumn<Vehicle, String> columnPlate;
    @FXML
    private TableColumn<Vehicle, String> columnNotes;

    public void initialize() throws SQLException, ClassNotFoundException {
        //Desactivar los botones hasta que haya algo seleccionado en la lista
        buttonModifyVehicle.disableProperty().setValue(true);
        buttonDeleteVehicle.disableProperty().setValue(true);
        //Los eventos necesarios se inicializan en un método aparte para facilitar la legibilidad del código
        setVehicleTableListeners();
        //Obtener la lista de vehículos de la base de datos e iniciarlizar la tabla
        loadTableView();
    }

    /***
     * Este método carga los datos en la tabla
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void loadTableView() throws SQLException, ClassNotFoundException {
        List<Vehicle> vehicles = vehicleDAO.readAll();
        ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList(vehicles);
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        columnNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        vehicleTable.getColumns().clear();
        vehicleTable.getColumns().setAll(columnBrand, columnModel, columnPlate, columnNotes);
        vehicleTable.setItems(vehicleList);
    }

    /*En el constructor sólo se pueden cargar los elementos que no formen parte de la vista
     * Me hubiera gustado realizar la inyección de dependencias para que las implementaciones fueran más sencillas
     * de intercambiar, pero no logré ponerlas en fucionamiento. */
    public MainSceneController() {
        database = new CockroachDatabase();
        personDAO = new PersonCrdbDao(database);
        vehicleDAO = new VehicleCrdbDao(database);
    }

    /***
     * Método que controla el evento del menú Info >> Información de la aplicación
     * @throws IOException
     */
    public void showInfoDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfoDialog-scene.fxml"));
        Scene infoDialogScene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(infoDialogScene);
        stage.showAndWait();
    }

    /***
     * Método que controla la eliminación del elemento seleccionado en la tabla.
     * Muestra un aviso previo a la eliminación.
     *
     * @param actionEvent
     */
    @FXML
    public void deleteVehicle(ActionEvent actionEvent) {
        Alert.AlertType confirmationType = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(confirmationType, "Se va a BORRAR un vehículo de la base de datos. Está seguro?");
        alert.initOwner(buttonDeleteVehicle.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                vehicleDAO.delete(selectedVehicle);
                loadTableView();
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Ha ocurrido algún error en la recarga de datos");
            }
        }


    }

    /***
     * Método que carga el stage para acceder a la funcionalidad de añadir un nuevo vehículo
     *
     * @param actionEvent
     * @throws IOException
     */
    public void newVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewVehicle-scene.fxml"));
        Scene newVehicleScene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setOnHiding(event -> {
            try {
                loadTableView();
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Ha ocurrido algún error en la recarga de datos");
            }
        });
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(newVehicleScene);
        stage.showAndWait();
    }

    /***
     * Evento que inicia el formulario para modificar el vehículo seleccionado
     *
     * @param actionEvent
     * @throws IOException
     */
    public void modifyVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyVehicle-scene.fxml"));
        Scene modifyVehicleScene = new Scene(fxmlLoader.load());
        //Obtención de la clase controladora
        ModifyVehicleController controller = fxmlLoader.getController();
        //Carga del vehículo seleccionado
        controller.setVehicleToModify(selectedVehicle);
        Stage stage = new Stage();
        //La llamada al evento 'onHiding' recarga los datos de la tabla tras haber modificado el vehículo
        stage.setOnHiding(event -> {
            try {
                loadTableView();
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("No ha podido cargarse los datos de la base de datos...");
            }
        });
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(modifyVehicleScene);
        stage.showAndWait();
    }

    /***
     * Método que inicia el stage de gestión de usuarios
     * @param actionEvent
     * @throws IOException
     */
    public void manageOwners(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageOwners-scene.fxml"));
        Scene manageOwnersScene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(manageOwnersScene);
        stage.showAndWait();
    }

    /***
     * Este método inicializa los eventos que se han utilizado en el controlador
     */
    private void setVehicleTableListeners(){
        vehicleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            //Actualizar la variable en función de la selección de la tabla
            selectedVehicle = newSelection;

            //Activar los botones 'modificar' y 'eliminar'
            buttonModifyVehicle.disableProperty().setValue(false);
            buttonDeleteVehicle.disableProperty().setValue(false);
        });
    }
}
