package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;


import jdbc.dao.HuespedesDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huespedes;

public class HuespedController {
	private HuespedesDAO huespedesDAO;
	
	public HuespedController() {
		Connection con = new ConnectionFactory().recuperaConexion();
		this.huespedesDAO = new HuespedesDAO(con);
	}
	public void guardar(Huespedes huesped) {
		this.huespedesDAO.guardar(huesped);		
	}
    public List<Huespedes> listarHuesped(String huespedBuscado) {
        return this.huespedesDAO.listarHuesped(huespedBuscado);
    }
	public int modificar(Integer id, String nombre, String apellido, Date fechanacimiento,
			String nacionalidad, String telefono, Integer idreserva) {
		return this.huespedesDAO.modificar(id, nombre, apellido, fechanacimiento, nacionalidad, telefono, idreserva);
	}
	public Object eliminar(Integer id) {
		return this.huespedesDAO.eliminar(id);
	}
}
