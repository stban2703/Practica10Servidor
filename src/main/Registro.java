package main;

public class Registro {
	
	String nombre;
	String cedula;
	
	public Registro(String nombre, String cedula) {
		this.nombre = nombre;
		this.cedula = cedula;
	}
	
	public Registro() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
}
