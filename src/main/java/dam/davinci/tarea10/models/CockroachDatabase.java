package dam.davinci.tarea10.models;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/***
 * Esta clase representa la conexión a la base de datos.
 *
 * Cualquier conexión JDBC se basa en el objeto 'Connection' de la propia API, por lo que no es necesario
 * crear ninguna interfaz.
 *
 * La clase utiliza un objeto properties para cargar los parámetros de la base de datos desde un archivo de propiedades
 * que tiene que estar ubicado en la carpeta 'resources'.
 *
 * Lo normal es utilizar la clase 'DriverManager' para obtener el objeto Connection, sin embargo, la documentación
 * de CockroachDB indica que es mejor utilizar la clase 'PGSimpleDataSource' por algo relativo a los pool de conexión y
 * la eficiencia. No entiendo los detalles pero sigo las instrucciones.
 */
public class CockroachDatabase implements DbFactory {
    private Properties dbProperties;
    private Connection connection;
    private PGSimpleDataSource ds;

    public CockroachDatabase(){
        dbProperties = new Properties();
        try {
            //Se puede cargar de esta manera por estar en la carpeta 'resources' que está en el classpath del proyecto
            dbProperties.load(getClass().getResourceAsStream("/cockroachdb.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ds = new PGSimpleDataSource();
        ds.setUrl(dbProperties.getProperty("url"));
        ds.setUser(dbProperties.getProperty("user"));
        ds.setPassword(dbProperties.getProperty("password"));
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ds.getConnection();
        }
        return connection;
    }
}