package dam.davinci.tarea10.controllers;

import dam.davinci.tarea10.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

/***
 * Controlador de la vista 'Gestionar propietarios'
 */
public class ManageOwnersController {

    public TableColumn<Person,String> nameColumn;
    public TableColumn<Person,String> surnameColumn;
    public TableColumn<Person, String> documentColumn;
    public TableView<Person> tableViewOwners;
    public Button buttonDeleteOwner;
    public TextField fieldName;
    public TextField fieldSurname;
    public TextField fieldNif;


    private DbFactory database;
    private PersonDAO personDAO;
    private ObservableList<Person> ownersList;
    private Person selectedOwner;

    public ManageOwnersController() throws SQLException {
        database = new CockroachDatabase();
        personDAO = new PersonCrdbDao(database);

    }

    public void initialize() {
        //Se deshabilita el botón borrar
        buttonDeleteOwner.disableProperty().setValue(true);
        //Se separa la lógica de inicialización de la tabla para poder llamarla desde otros métodos
        initializeTableView();
        setListeners();
    }

    private void initializeTableView() {
        try{
            ownersList = FXCollections.observableArrayList(personDAO.readAll());
        } catch (SQLException e) {
            System.err.println("Ha ocurrido algún error leyendo la tabla");
            System.err.println(e.getMessage());
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        documentColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        tableViewOwners.setItems(ownersList);
        tableViewOwners.refresh();
    }


    private void setListeners(){
        //Este evento detecta el valor seleccionado en la tabla y lo inserta en selectedOwner
        //Cuando eso ocurre, se habilita el botón 'Borrar'
        tableViewOwners.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOwner = newValue;
            buttonDeleteOwner.disableProperty().setValue(false);
        });
    }

    public void closeWindow(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void addNewOwner(ActionEvent actionEvent) {
        if (!fieldName.getText().isEmpty()  || !fieldNif.getText().isEmpty()) {
            Person newOwner = new Person(fieldName.getText(), fieldNif.getText());
            if (fieldSurname.getText() != null) newOwner.setSurname(fieldSurname.getText());
            try{
                personDAO.create(newOwner);
                initializeTableView();
            } catch (SQLException e) {
                System.err.println("Ha ocurrido algún error en la inserción del nuevo registro");
                System.err.println(e.getMessage());
            }
        }
    }

    public void deleteSelectedOwner(ActionEvent actionEvent) {
        try {
            personDAO.delete(selectedOwner);
            initializeTableView();
        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar el propietario de la base de datos.");
            System.err.println(e.getMessage());
        }
    }

}
