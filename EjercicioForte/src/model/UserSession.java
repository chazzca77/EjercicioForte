package model;

public class UserSession {

	private String nombre;
	private int idUsuario;
	
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserSession(String nombre, int idUsuario) {
		super();
		this.nombre = nombre;
		this.idUsuario = idUsuario;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return idUsuario;
	}


	@Override
	public String toString() {
		return "UserSession [nombre=" + nombre + ", idUsuario=" + idUsuario + "]";
	}

	
}
