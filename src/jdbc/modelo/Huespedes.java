package jdbc.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Huespedes {

		private int id;
		private String nombre;
		private String apellido;
		private Date fechanacimiento;
		private String nacionalidad;
		private String telefono;
		private int idreserva;

	
		public Huespedes(String nombre, String apellido, Date fechanacimiento, String nacionalidad, String telefono,
				int idreserva) {
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.fechanacimiento = fechanacimiento;
			this.nacionalidad = nacionalidad;
			this.telefono = telefono;
			this.idreserva = idreserva;
		}

		public Huespedes (Date fechaN) {
			this.fechanacimiento = fechaN; 
		}
		
	public Huespedes(int id, String nombre, String apellido, Date fechanacimiento, String nacionalidad, String telefono, int idreserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechanacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idreserva = idreserva;

		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}
}
