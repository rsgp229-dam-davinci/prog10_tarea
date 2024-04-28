package dam.davinci.tarea10.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleCrdbDao implements VehicleDAO{

    private DbFactory database;

    public VehicleCrdbDao(DbFactory database) {
        this.database = database;
    }

    @Override
    public Vehicle read(String id) throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM vehicles WHERE uuid = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String uuid = rs.getString("id");
            String plate = rs.getString("plate");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            int year = rs.getInt("year");
            int kilometers = rs.getInt("kilometers");
            String ownerNif = rs.getString("ownerid");
            String notes = rs.getString("notes");
            return new Vehicle(uuid, plate, brand, model, year, kilometers, ownerNif, notes);
        } else {
            return null;
        }
    }

    @Override
    public List<Vehicle> readAll() throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM vehicles");
        ResultSet rs = stmt.executeQuery();
        List<Vehicle> vehicles = new ArrayList<>();
        while (rs.next()) {
            String uuid = rs.getString("id");
            String plate = rs.getString("plate");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            int year = rs.getInt("year");
            int kilometers = rs.getInt("kilometers");
            String ownerId = rs.getString("ownerId");
            String notes = rs.getString("notes");
            Vehicle vehicle = new Vehicle(uuid, plate, brand, model, year, kilometers, ownerId, notes);
            vehicles.add(vehicle);
        }
        rs.close();
        stmt.close();
        con.close();
        return vehicles;
    }

    @Override
    public void create(Vehicle vehicle) throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO vehicles (plate, brand, model, year, kilometers, ownerid, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, vehicle.getPlate());
        stmt.setString(2, vehicle.getBrand());
        stmt.setString(3, vehicle.getModel());
        stmt.setInt(4, vehicle.getYear());
        stmt.setInt(5, vehicle.getKilometers());
        stmt.setString(6, vehicle.getOwnerId());
        stmt.setString(7, vehicle.getNotes());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void update(Vehicle vehicle) throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE vehicles SET plate = ?, brand = ?, model = ?, year = ?, " +
                        "kilometers = ?, owner_nif = ?, notes = ? WHERE uuid = ?");
        stmt.setString(1, vehicle.getPlate());
        stmt.setString(2, vehicle.getBrand());
        stmt.setString(3, vehicle.getModel());
        stmt.setInt(4, vehicle.getYear());
        stmt.setInt(5, vehicle.getKilometers());
        stmt.setString(6, vehicle.getOwnerId());
        stmt.setString(7, vehicle.getNotes());
        stmt.setString(8, vehicle.getUuid());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    @Override
    public void delete(Vehicle vehicle) throws SQLException {
        Connection con = database.getConnection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM vehicles WHERE id = ?");
        stmt.setString(1, vehicle.getUuid());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
}