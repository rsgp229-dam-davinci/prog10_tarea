package dam.davinci.tarea10.models;

import java.sql.Connection;
import java.sql.SQLException;

/***
 * Esta interfaz funcional representa cualquier objeto, normalmente una base de dato, que provea objetos Connection
 */
public interface DbFactory {
    Connection getConnection() throws SQLException;
}
