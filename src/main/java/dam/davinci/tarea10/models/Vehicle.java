package dam.davinci.tarea10.models;

import javafx.beans.property.*;

import java.util.Objects;

/***
 * Esta clase representa un vehículo. Al igual que Person es un Bean de Fx.
 * La característica cotidiana más distintiva de un vehículo es la matrícula. Realmente un vehículo puede rematricularse,
 * por lo que, de cara a un modelo, debería ser el número de serie para ser lo más estricto posible de cara a una
 * posterior normalización.
 *
 * En todo caso se ha elegido la matrícula como rasgo 'principal' de un vehículo.
 *
 * @author Rafael Sánchez González-Palacios
 */
public class Vehicle {
    private ReadOnlyStringWrapper uuid;
    private StringProperty plate;
    private StringProperty brand;
    private StringProperty model;
    private IntegerProperty year;
    private IntegerProperty kilometers;
    private StringProperty notes;
    private StringProperty ownerId;

    public Vehicle(String uuid, String plate, String brand,
                   String model, int year, int kilometers,
                   String ownerId, String notes) {
        this.uuid = new ReadOnlyStringWrapper(uuid);
        this.plate = new SimpleStringProperty(plate);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
        this.kilometers = new SimpleIntegerProperty(kilometers);
        this.ownerId = new SimpleStringProperty(ownerId);
        this.notes = new SimpleStringProperty(notes);
    }

    /**
     * <p>Constructor mínimo para un vehículo.</p>
     *
     * <p>Recientemente he descubierto Objects.requireNonNull(). No es más que un lanzamiento de excepción, pero
     * me resulta un buen estilo cuando se requiere asegurarse de que no se inician objetos malformados.
     * En el caso del modelo, no debe iniciarse por ningún medio un vehículo sin matrícula; simplemente no debe
     * existir.</p>
     *
     * @param plate La matrícula del nuevo vehículo (No debe ser null)
     * @param brand La marca del nuevo vehículo (No debe ser null)
     */
    public Vehicle(String plate, String brand)  {
        this.brand = new SimpleStringProperty(brand);
        this.plate = new SimpleStringProperty(plate);
        this.uuid = new ReadOnlyStringWrapper();
        this.model = new SimpleStringProperty();
        this.year = new SimpleIntegerProperty();
        this.kilometers = new SimpleIntegerProperty();
        this.ownerId = new SimpleStringProperty();
        this.notes = new SimpleStringProperty();
    }

    public String getUuid() {
        return uuid.get();
    }

    public ReadOnlyStringProperty uuidProperty() {
        return uuid.getReadOnlyProperty();
    }

    public void setUuid(String uuid) {
        this.uuid.set(uuid);
    }

    public String getPlate() {
        return plate.get();
    }

    public StringProperty plateProperty() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate.set(plate);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getKilometers() {
        return kilometers.get();
    }

    public IntegerProperty kilometersProperty() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers.set(kilometers);
    }

    public String getOwnerId() {
        return ownerId.get();
    }

    public StringProperty ownerIdProperty() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId.set(ownerId);
    }

    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle that = (Vehicle) o;
        return Objects.equals(getPlate(), that.getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPlate());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBrand()).append(" ");
        sb.append(getModel()).append(" ");
        sb.append(getPlate()).append(" ");
        return sb.toString();
    }
}
