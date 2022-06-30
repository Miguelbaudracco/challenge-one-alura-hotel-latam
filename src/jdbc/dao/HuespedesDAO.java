package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import jdbc.modelo.Huespedes;
import jdbc.modelo.Reserva;

public class HuespedesDAO {
		private Connection con;

		public HuespedesDAO(Connection connection) {
			this.con = connection;
		}
		public void guardar(Huespedes huesped) {
	        try {
	            String sql = "INSERT INTO huespedes "
	                    + "(nombre, apellido, fechanacimiento, nacionalidad, telefono, idreserva)"
	                    + " VALUES (?, ?, ?, ?, ?, ?)";
	        	PreparedStatement statement;
	                statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                        
	    
	            try (statement) {
	                statement.setString(1, huesped.getNombre());
	                statement.setString(2, huesped.getApellido());
	                statement.setDate(3, huesped.getFechanacimiento());
	                statement.setString(4, huesped.getNacionalidad());
	                statement.setString(5, huesped.getTelefono());
	                statement.setInt(6, huesped.getIdreserva());

	    
	                statement.executeUpdate();
	    
	                final ResultSet resultSet = statement.getGeneratedKeys();
	    
	                try (resultSet) {
	                    while (resultSet.next()) {
	                        huesped.setId(resultSet.getInt(1));
	                        
	                        System.out.println(String.format("Fue insertado el Huesped: %s", huesped));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    
		}
		
	    public List<Huespedes> listarHuesped(String huespedBuscado) { 
	        List<Huespedes> resultado = new ArrayList<>();

	        try {
	            String sql = "SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM HUESPEDES WHERE NOMBRE = ? OR APELLIDO = ? OR IDRESERVA = ?" ;
//	            String sql = "SELECT R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.VALOR, R.FORMAPAGO, H.ID, H.NOMBRE, H.APELLIDO, H.FECHANACIMIENTO, H.NACIONALIDAD, H.TELEFONO, H.IDRESERVA FROM RESERVAS R INNER JOIN HUESPEDES H ON  H.IDRESERVA = R.ID  WHERE H.NOMBRE LIKE ?";
	            
	            System.out.println(sql);
	             
	            final PreparedStatement statement = con
	                    .prepareStatement(sql);
	            statement.setString(1, huespedBuscado);
	            statement.setString(2, huespedBuscado);
	            boolean isNumeric = huespedBuscado.chars().allMatch( Character::isDigit );
	            if (isNumeric) {
	            	int  huespedBuscado1 = Integer.valueOf(huespedBuscado);
	            	statement.setInt(3, huespedBuscado1);
		            }else {        
		            	statement.setInt(3, 0);
		            }
		        try (statement) {
	                final ResultSet resultSet = statement.executeQuery();

	                try (resultSet) {
	                    while (resultSet.next()) {
	                        resultado.add(new Huespedes(
	                                resultSet.getInt("ID"),
	                                resultSet.getString("NOMBRE"),
	                                resultSet.getString("APELLIDO"),
	                                resultSet.getDate("FECHANACIMIENTO"),
	                                resultSet.getString("NACIONALIDAD"),
	                                resultSet.getString("TELEFONO"),
	                                resultSet.getInt("IDRESERVA")
	                                ));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        return resultado;
	    }
		public int modificar(Integer id, String nombre, String apellido, Date fechanacimiento, String nacionalidad,
				String telefono, Integer idreserva) {
	        try {
	            final PreparedStatement statement = con.prepareStatement(
	                    "UPDATE HUESPEDES SET "
	                    + " NOMBRE = ?, "
	                    + " APELLIDO = ?, "
	                    + " FECHANACIMIENTO = ?, "
	                    + " NACIONALIDAD = ?, "
	                    + " TELEFONO = ?, "
	                    + " IDRESERVA = ? "
	                    + " WHERE ID = ?");

	            try (statement) {
	                statement.setString(1, nombre);
	                statement.setString(2, apellido);
	                statement.setDate(3, fechanacimiento);
	                statement.setString(4, nacionalidad);
	                statement.setString(5, telefono);
	                statement.setInt(6, idreserva);
	                statement.setInt(7, id);
	                statement.execute();

	                int updateCount = statement.getUpdateCount();

	                return updateCount;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
		}
		public Object eliminar(Integer id) {
	        try {
	            final PreparedStatement statement = 
	            		con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

	            try (statement) {
	                statement.setInt(1, id);
	                statement.execute();

	                int updateCount = statement.getUpdateCount();

	                return updateCount;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

		}
	    

		
}
