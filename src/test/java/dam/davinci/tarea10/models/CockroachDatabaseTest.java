package dam.davinci.tarea10.models;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class CockroachDatabaseTest {

    CockroachDatabase db = new CockroachDatabase();

    @Test
    void getConnection() {
        //Comprobamos que no lanza ninguna excepción
        assertDoesNotThrow(() -> {
            try (Connection con = db.getConnection()) {
                //Comprobamos que el objeto no es null
                assertNotNull(con);
                //Comprobamos que estamos conectados a la base de datos indicada en la cadena de conexión
                System.out.println(con.getCatalog());
            }
        });
    }
}