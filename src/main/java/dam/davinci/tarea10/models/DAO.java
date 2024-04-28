package dam.davinci.tarea10.models;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
     T read (String id) throws SQLException;
     List<T> readAll() throws SQLException;
     void create(T t) throws SQLException;
     void update(T t) throws SQLException;
     void delete(T t) throws SQLException;
}