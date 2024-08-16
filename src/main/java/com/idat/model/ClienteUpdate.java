package com.idat.model;

public class ClienteUpdate {
	
	private String nombre;
    private String email;
    private String celular;
    private String password;
    
    
	public ClienteUpdate() {
		super();
	}
	public ClienteUpdate(String nombre, String email, String celular, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.celular = celular;
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
