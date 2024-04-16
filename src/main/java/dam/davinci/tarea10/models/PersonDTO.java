package dam.davinci.tarea10.models;

/*  Las clases utilizadas para la creación de los campos son 'properties' de javaFx
 *  Esto debe ser así para que los controles funcionen adecuadamente, por ejemplo para rellenar el TableView.
 *  También permite el uso de eventos de manera más directa mediante el API de 'binding' de Fx, sin tener que
 *  crear eventos para cada caso, excepto donde el evento sea muy concreto. */

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/***
 * Esta clase representa el DTO de la clase personas.
 *
 * Todas las clases se diseñan como FxBeans, por lo que los campos son 'properties' de JavaFx
 *
 * @author: Rafael Sánchez González-Palacios
 */
public class PersonDTO {
    private ReadOnlyStringWrapper id;
    private StringProperty name;
    private StringProperty surname;
    private StringProperty dni;

    public PersonDTO(String id, String name, String surname, String dni) throws IllegalArgumentException{
        if (Helper.checkDni(dni)){
            this.dni = new SimpleStringProperty(dni);
        } else {
            throw new IllegalArgumentException("Invalid DNI. DNI must be a valid DNI.");
        }
        if (Helper.checkUUID(id)){
            this.id = new ReadOnlyStringWrapper(id);
        } else {
            throw new IllegalArgumentException("Invalid ID. Id must be a valid UUID.");
        }
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    public String getId() {
        return id.get();
    }

    public ReadOnlyStringProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    // La comparación y el hashcode se establece en el elemento más distintivo de una persona, que es su DNI.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getDni(), personDTO.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDni());
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", dni=" + dni +
                '}';
    }
}
