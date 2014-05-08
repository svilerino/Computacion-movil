package com.cmovil.pathfinder;

public class ActivarModel {

	private ActivarActivity _activity;

	private String _email;
	private String _contrasena;
	private String _repetirContrasena;

	public ActivarModel(ActivarActivity a) {
		
		this._activity = a;
	}

	public void guardarDatos(String email, String contrasena,
			String repetirContrasena) {
		
		this._email = email;
		this._contrasena = contrasena;
		this._repetirContrasena = repetirContrasena;
	}

	public boolean sonDatosValidos() {
		
		return _email != null && _contrasena != null && _repetirContrasena != null
				&& _email.length() > 0 && _contrasena.length() > 0
				&& _contrasena.equals(_repetirContrasena);
	}

	public void activarDispositivo() {
		
		(new ActivarDispositivoAsyncTask(_activity))
			.execute(_email, _contrasena);
	}

	public String getEmail() {
		
		return this._email;
	}

	public String getContrasena() {
		
		return this._contrasena;
	}

}
