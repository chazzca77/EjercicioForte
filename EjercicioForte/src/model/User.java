package model;

import Utils.EncrypterHelper;

public class User {
	private int id;
	private String nombre;
	private String apellidos;
	private String username;
	private String password;
	private String altaUsuario;
	
	private boolean isValid;

	public User(int id, String nombre, String apellidos, String username, String password, String altaUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.username = username;
		this.password = password;
		this.altaUsuario = altaUsuario;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPasswordE(String password){
		this.password = EncrypterHelper.encrypMd5Hex(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAltaUsuario() {
		return altaUsuario;
	}

	public void setAltaUsuario(String altaUsuario) {
		this.altaUsuario = altaUsuario;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", username=" + username
				+ ", password=" + password + ", altaUsuario=" + altaUsuario + " isValid="
				+ isValid + "]";
	}

}
