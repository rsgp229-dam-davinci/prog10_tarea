package dam.davinci.tarea10.controllers;

import dam.davinci.tarea10.models.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ModifyVehicleController {

    public TextField fieldBrand;
    public TextField fieldModel;
    public TextField fieldPlate;
    public TextField fieldKilometers;
    public ComboBox<Person> comboOwners;
    public TextArea fieldDescription;
    private DbFactory database;
    private PersonDAO personDAO;
    private VehicleDAO vehicleDAO;
    private Vehicle vehicleToModify;
    private List<Person> persons;

    public ModifyVehicleController() {
        database = new CockroachDatabase();
        personDAO = new PersonCrdbDao(database);
        vehicleDAO = new VehicleCrdbDao(database);
    }

    public void initialize() throws SQLException {
        comboOwners.getItems().addAll(personDAO.readAll());
    }

    //TODO Falta la lógica para añadir el propietario

    public void cancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void saveChanges(ActionEvent actionEvent) throws SQLException{
        PreparedStatement statement = database.getConnection().prepareStatement(
                "UPDATE vehicles SET brand=?, model=?, plate=?, kilometers=?, " +
                        "notes=?, ownerid=? WHERE id=?");
        statement.setString(1, fieldBrand.getText());
        statement.setString(2, fieldModel.getText());
        statement.setString(3, fieldPlate.getText());
        statement.setInt(4, Integer.parseInt(fieldKilometers.getText()));
        statement.setString(5, fieldDescription.getText());
        statement.setString(6, comboOwners.getSelectionModel().getSelectedItem().getId());
        statement.setString(7, vehicleToModify.getUuid());
        statement.executeUpdate();
        statement.close();
    }



    /***
     * Utilizando FXML no se pueden pasar parámetros a los controladores por lo que
     * se crea un método auxiliar que se llama tras la carga.
     * @param vehicleToModify
     */
    public void setVehicleToModify(Vehicle vehicleToModify) {
        this.vehicleToModify = vehicleToModify;
        fieldBrand.setText(vehicleToModify.getBrand());
        fieldModel.setText(vehicleToModify.getModel());
        fieldPlate.setText(vehicleToModify.getPlate());
        fieldKilometers.setText(String.valueOf(vehicleToModify.getKilometers()));
        fieldDescription.setText(vehicleToModify.getNotes());
        try {
            Person owner = personDAO.read(vehicleToModify.getOwnerId());
            comboOwners.getSelectionModel().select(owner);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
