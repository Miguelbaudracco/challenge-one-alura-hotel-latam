package jdbc.controller;

import java.sql.Connection;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huespedes;
import jdbc.modelo.Reserva;

public class ReservaController {
		private ReservaDAO reservaDAO;
	
		public ReservaController() {
			Connection con = new ConnectionFactory().recuperaConexion();
			this.reservaDAO = new ReservaDAO(con);
		}
	public int guardar(Reserva reserva) {
		return this.reservaDAO.guardar(reserva);		
	}
    public List<Reserva> listarReserva(int idBuscado) {
        return this.reservaDAO.listarReserva(idBuscado);
    }
    public List<Reserva> listarHuespedReserva() {
        return reservaDAO.listarHuespedReserva();
    }
	public Object eliminar(Integer idreserva) {
		return this.reservaDAO.eliminar(idreserva);
	}

}
