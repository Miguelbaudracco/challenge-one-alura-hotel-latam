package jdbc.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class Reserva {
	
	private Integer id;
	private Date fechaE;
	private Date fechaS;
	private String valor;
	private String formaPago;
    List<Huespedes> resultado = new ArrayList<>();
    private List<Huespedes> huespedes = new ArrayList<>();
    
	public Reserva(Date fechaE, Date fechaS, String valor, String formaPago) {
	super();
	this.fechaE = fechaE;
	this.fechaS = fechaS;
	this.valor = valor;
	this.formaPago = formaPago;
}

	public Reserva(Date fechaE, Date fechaS) {
		this.fechaE = fechaE;
		this.fechaS = fechaS;
	}

	public Reserva(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		this.id = id;
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaE() {
		return fechaE;
	}

	public Date getFechaS() {
		return fechaS;
	}

	public String getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}
	   public List<Huespedes> getHuespedes() {
	        return this.getHuespedes();
	    }

	public void agregar(Huespedes huesped) {
		this.huespedes.add(huesped);
		
	}

}
