package dam.davinci.tarea10.controllers;

import dam.davinci.tarea10.models.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.List;

public class NewVehicleSceneController {

    public TextField fieldBrand;
    public TextField fieldModel;
    public TextField fieldPlate;
    public TextField fieldKilometers;
    public ComboBox<Person> comboOwners;
    public TextArea fieldDescription;
    private DbFactory database;
    private PersonDAO personDAO;
    private VehicleDAO vehicleDAO;
    private Vehicle insertVehicle;

    public NewVehicleSceneController() {
        this.database = new CockroachDatabase();
        this.personDAO = new PersonCrdbDao(database);
        this.vehicleDAO = new VehicleCrdbDao(database);
    }

    public void initialize(){
        List<Person> owners = null;
        try {
            owners = personDAO.readAll();
        } catch (SQLException e) {
            System.err.println("Ha ocurrido algún error durante la lectura de los propietarios");
        }
        comboOwners.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
            @Override
            public ListCell<Person> call(ListView<Person> param) {
                return new ListCell<Person>() {
                    @Override
                    protected void updateItem(Person item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName() + " " + item.getNif());  // Muestra solo el campo deseado
                        }
                    }
                };
            }
        });
        comboOwners.getItems().addAll(owners);
    }

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void addNewVehicle(ActionEvent actionEvent) throws SQLException {
        insertVehicle = new Vehicle(fieldPlate.getText(), fieldBrand.getText());
        insertVehicle.setModel(fieldModel.getText());
        insertVehicle.setKilometers(Integer.parseInt(fieldKilometers.getText()));
        if (comboOwners.getSelectionModel().getSelectedItem() != null) {
            insertVehicle.setOwnerId(comboOwners.getSelectionModel().getSelectedItem().getId());
        }
        insertVehicle.setNotes(fieldDescription.getText());
            vehicleDAO.create(insertVehicle);
    }

}
