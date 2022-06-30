package jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class ProbarConexion {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection con = factory.recuperaConexion();

        System.out.println("Cerrando la conexión");

        con.close();
    }
}
