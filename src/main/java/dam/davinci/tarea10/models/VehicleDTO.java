package dam.davinci.tarea10.models;

import javafx.beans.property.*;

import java.util.Objects;

/***
 * Esta clase representa un vehículo. Al igual que Person es un Bean de Fx.
 * @author Rafael Sánchez González-Palacios
 */
public class VehicleDTO {
    private ReadOnlyStringWrapper uuid;
    private StringProperty plate;
    private StringProperty brand;
    private StringProperty model;
    private IntegerProperty year;
    private IntegerProperty kilometers;
    private StringProperty notes;
    private StringProperty ownerDni;

    public VehicleDTO(String uuid, String plate, String brand,
                      String model, int year, int kilometers,
                      String ownerDni, String notes) {
        this.uuid = new ReadOnlyStringWrapper(uuid);
        this.plate = new SimpleStringProperty(plate);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
        this.kilometers = new SimpleIntegerProperty(kilometers);
        this.ownerDni = new SimpleStringProperty(ownerDni);
        this.notes = new SimpleStringProperty(notes);
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

    public String getOwnerDni() {
        return ownerDni.get();
    }

    public StringProperty ownerDniProperty() {
        return ownerDni;
    }

    public void setOwnerDni(String ownerDni) {
        this.ownerDni.set(ownerDni);
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
        VehicleDTO that = (VehicleDTO) o;
        return Objects.equals(getPlate(), that.getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPlate());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VehicleDTO{");
        sb.append("uuid=").append(uuid.get());
        sb.append(", plate=").append(plate.get());
        sb.append(", brand=").append(brand.get());
        sb.append(", model=").append(model.get());
        sb.append(", year=").append(year.get());
        sb.append(", kilometers=").append(kilometers.get());
        sb.append(", notes=").append(notes.get());
        sb.append(", ownerDni=").append(ownerDni.get());
        sb.append("}");
        return sb.toString();
    }
}
