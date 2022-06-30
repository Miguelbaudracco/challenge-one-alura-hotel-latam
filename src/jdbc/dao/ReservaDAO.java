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

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection connection) {
		this.con = connection;
	}

	public int guardar(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservas "
                    + "(fechaentrada, fechasalida, valor, formapago)"
                    + " VALUES (?, ?, ?, ?)";
        	PreparedStatement statement;
                statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        
    
            try (statement) {
                statement.setDate(1, reserva.getFechaE());
                statement.setDate(2, reserva.getFechaS());
                statement.setString(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
    
                statement.executeUpdate();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el producto: %s", reserva));
                        
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reserva.getId();
    
	}
    public List<Reserva> listarReserva(int idBuscado) {
        List<Reserva> resultado = new ArrayList<>();
        try {
            String sql = "SELECT ID, FECHAENTRADA, FECHASALIDA, VALOR, FORMAPAGO FROM RESERVAS WHERE ID = ?";
            
            System.out.println(sql);
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);
            statement.setInt(1, idBuscado);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reserva(
                        		resultSet.getInt("ID"),
                                resultSet.getDate("FECHAENTRADA"),
                                resultSet.getDate("FECHASALIDA"),
                                resultSet.getString("VALOR"),
                                resultSet.getString("FORMAPAGO")
                                ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    
    public List<Reserva> listarHuespedReserva() {
        List<Reserva> resultado = new ArrayList<>();

        try {
            String sql = "SELECT R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.VALOR, R.FORMAPAGO, H.ID, H.NOMBRE, H.APELLIDO, H.FECHANACIMIENTO, H.NACIONALIDAD, H.TELEFONO FROM RESERVAS R INNER JOIN HUESPEDES H ON  R.ID = H.IDRESERVA WHERE H.NOMBRE LIKE '%'?'%'";
            System.out.println(sql);
            
            final PreparedStatement statement = con.prepareStatement(
                    sql);
    
            try (statement) {
    
                final ResultSet resultSet = statement.executeQuery();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        int reservaId = resultSet.getInt("R.ID");
                        Date reservaFechaE = resultSet.getDate("R.FECHAENTRADA");
                        Date reservaFechaS = resultSet.getDate("R.FECHASALIDA");
                        String reservaValor = resultSet.getString("R.VALOR");
                        String reservaFormaPago = resultSet.getString("R.FORMAPAGO");

                        
                        Reserva reserva = resultado
                            .stream()
                            .filter(cat -> cat.getId().equals(reservaId))
                            .findAny().orElseGet(() -> {
                                Reserva cat = new Reserva(
                                        reservaId, reservaFechaE, reservaFechaS, reservaValor, reservaFormaPago);
                                resultado.add(cat);
                                
                                return cat;
                            });
                        
                        Huespedes huesped = new Huespedes(
                                resultSet.getInt("H.ID"),
                                resultSet.getString("H.NOMBRE"),
                                resultSet.getString("H.APELLIDO"),
                                resultSet.getDate("H.FECHANACIMIENTO"),
                                resultSet.getString("H.NACIONALIDAD"),
                                resultSet.getString("H.TELEFONO"),
                                resultSet.getInt("H.IDRESERVA")
                                
                                );
                        
                        reserva.agregar(huesped);
                    }                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

	public Object eliminar(Integer idreserva) {
        try {
            final PreparedStatement statement = 
            		con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");

            try (statement) {
                statement.setInt(1, idreserva);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		
	}		

}
