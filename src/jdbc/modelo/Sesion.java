package jdbc.modelo;

public class Sesion {
	
	public String usuario, password, idEntreFrame;
	private static Sesion sesion;
	
	private Sesion() {	}

	public synchronized static Sesion getInstance() {
		if (sesion == null) {
			sesion = new Sesion();
		}
		return sesion;
	}
}
