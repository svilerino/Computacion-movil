package com.cmovil.pathfinder;

import java.util.ArrayList;

public class IngresarModel {

	private IngresarActivity _activity;

	private String _email;
	private String _contrasena;

	private ArrayList<PuntoDeRecorrido> _recorrido;

	public IngresarModel(IngresarActivity a) {
		
		this._activity = a;
	}

	public void guardarDatos(String email, String contrasena) {
		
		this._email = email;
		this._contrasena = contrasena;
	}

	public boolean sonDatosValidos() {
		
		return _email != null && _contrasena != null
				&& _email.length() > 0 && _contrasena.length() > 0;
	}

	public void descargarRecorrido() {
		
		(new DescargarRecorridoAsyncTask(_activity))
			.execute(_email, _contrasena);
	}

	public ArrayList<PuntoDeRecorrido> getRecorrido() {

		return _recorrido;
	}

	public void setRecorrido(ArrayList<PuntoDeRecorrido> recorrido) {

		this._recorrido = recorrido;
	}
}
