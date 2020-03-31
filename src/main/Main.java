package main;

import java.util.ArrayList;

import comm.ComunicacionTCP;
import comm.ComunicacionTCP.OnMessageListener;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener {

	private ComunicacionTCP comunicacionTCP;
	private ArrayList<Registro> listaRegistro;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	public void settings() {
		size(500, 500);
	}

	public void setup() {
		comunicacionTCP = new ComunicacionTCP();
		listaRegistro = new ArrayList<Registro>();
		comunicacionTCP.setObserver(this);
		comunicacionTCP.esperarConexion();

	}

	public void draw() {
		background(0);
	}

	@Override
	public void onMessage(String mensaje) {
		// TODO Auto-generated method stub
		if (mensaje.startsWith("reg")) {
			String[] datos = mensaje.split(",");
			String nombre = datos[1];
			String cedula = datos[2];

			listaRegistro.add(new Registro(nombre, cedula));
			//System.out.println(listaRegistro.get(0).getNombre());
		}

		if (mensaje.startsWith("list")) {
			for (int i = 0; i < listaRegistro.size(); i++) {
				String nombre = listaRegistro.get(i).getNombre();
				String cedula = listaRegistro.get(i).getCedula();
				comunicacionTCP.mandarMensaje(nombre + "," + cedula);
			}
		}

	}
}
