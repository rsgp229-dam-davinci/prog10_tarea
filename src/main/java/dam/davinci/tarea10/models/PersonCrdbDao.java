package dam.davinci.tarea10.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonCrdbDao implements PersonDAO{

    private DbFactory database;

    public PersonCrdbDao(DbFactory database) {
        this.database = database;
    }

    @Override
    public Person read(String id) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT id, name, surname, nif FROM persons WHERE id = ?");
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        Person person = null;
        if (rs.next()) {
            String rsid = rs.getString("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String nif = rs.getString("nif");
            person = new Person(rsid, name, surname, nif);
        }
        conn.close();
        statement.close();
        rs.close();
        return person;

    }

    @Override
    public List<Person> readAll() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM persons");
        ResultSet rs = statement.executeQuery();
        List<Person> persons = new ArrayList<Person>();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String nif = rs.getString("nif");
            persons.add(new Person(id, name, surname, nif));
        }
        conn.close();
        statement.close();
        rs.close();
        return persons;
    }

    @Override
    public void create(Person person) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO persons (name, surname, nif) values (?,?,?)");
        statement.setString(1, person.getName());
        statement.setString(2, person.getSurname());
        statement.setString(3, person.getNif());
        statement.executeUpdate();
        conn.close();
        statement.close();
    }

    @Override
    public void update(Person person) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE persons SET name = ?, surname = ?, nif = ? WHERE id = ?");
        statement.setString(1, person.getName());
        statement.setString(2, person.getSurname());
        statement.setString(3, person.getNif());
        statement.setString(4, person.getId());
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    @Override
    public void delete(Person person) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM persons WHERE id = ?");
        statement.setString(1, person.getId());
        statement.executeUpdate();
        statement.close();
        conn.close();
    }
}
