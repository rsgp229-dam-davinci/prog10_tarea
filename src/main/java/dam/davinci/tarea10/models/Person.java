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
public class Person {
    private ReadOnlyStringWrapper id;
    private StringProperty name;
    private StringProperty surname;
    private StringProperty nif;

    /***
     * Este constructor se utilizará para la importación del modelo desde la base de datos.
     *
     * @param id
     * @param name
     * @param surname
     * @param nif
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Person(String id, String name, String surname, String nif) throws NullPointerException, IllegalArgumentException{
        Objects.requireNonNull(id, "El parámetro ID no puede ser nulo");
        if (Helper.checkUUID(id)){
            this.id = new ReadOnlyStringWrapper(id);
        } else {
            throw new IllegalArgumentException("UUID no válido");
        }
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.nif = new SimpleStringProperty(nif);
    }

    /***
     * Este constructor se utilizará desde la aplicación. Contiene los datos mínimos requeridos marcados
     * como 'not null' en la base de datos.
     * @param name
     * @param nif
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Person(String name, String nif) throws NullPointerException {
        Objects.requireNonNull(name);
        Objects.requireNonNull(nif);
        this.name = new SimpleStringProperty(name);
        this.nif = new SimpleStringProperty(nif);
        this.id = new ReadOnlyStringWrapper();
        this.surname = new SimpleStringProperty();
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

    public String getNif() {
        return nif.get();
    }

    public StringProperty nifProperty() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif.set(nif);
    }

    // La comparación y el hashcode se establece en el elemento más distintivo de una persona, que es su DNI.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getNif(), person.getNif());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNif());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" ");
        if (getSurname() != null) sb.append(getSurname()).append(" ");
        sb.append(getNif()).append(" ");
        return sb.toString();

    }
}
