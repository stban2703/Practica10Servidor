package main;

import java.util.ArrayList;

import com.google.gson.Gson;

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
	public void onMessage(String json) {

		// Recibir nuevos registros
		if (json.startsWith("{")) {

			Gson gson = new Gson();
			Registro registro = gson.fromJson(json, Registro.class);

			listaRegistro.add(registro);
			
		}

		// Enviar lista a Android
		if (json.startsWith("list")) {
			
			Gson gson = new Gson();
			String registros = gson.toJson(listaRegistro);

			comunicacionTCP.mandarMensaje(registros);

		}

	}
}
