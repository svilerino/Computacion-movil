package com.cmovil.pathfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivarView {

	private ActivarActivity _activity;
	
	private EditText _campoEmail;
	private EditText _campoContrasena;
	private EditText _campoRepetirContrasena;
	private Button _botonActivar;

	public ActivarView(ActivarActivity a) {
		
		this._activity = a;

		this._campoEmail = (EditText) _activity.findViewById(R.id.campoEmailA);
		this._campoContrasena = (EditText) _activity.findViewById(R.id.campoContrasenaA);
		this._campoRepetirContrasena = (EditText) _activity.findViewById(R.id.campoRepetirContrasenaA);
		this._botonActivar = (Button) _activity.findViewById(R.id.botonActivarA);
	}

	public void activarModoEspera() {
		_campoEmail.setEnabled(false);
		_campoContrasena.setEnabled(false);
		_campoRepetirContrasena.setEnabled(false);
		_botonActivar.setEnabled(false);
	}

	public void desactivarModoEspera() {
		_campoEmail.setEnabled(true);
		_campoContrasena.setEnabled(true);
		_campoRepetirContrasena.setEnabled(true);
		_botonActivar.setEnabled(true);
	}

	public String getEmail() {
		
		return _campoEmail.getText().toString();
	}

	public String getContrasena() {
		
		return _campoContrasena.getText().toString();
	}

	public String getRepetirContrasena() {
		
		return _campoRepetirContrasena.getText().toString();
	}

	public void mostrarAviso(int refTitulo, int refMensaje) {
		
		new AlertDialog.Builder(_activity)
				.setTitle(_activity.getString(refTitulo))
				.setMessage(_activity.getString(refMensaje))
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {}
				})
				.create().show();
	}

	public void mostrarMensaje(int refMensaje) {
		
		Toast toast = Toast.makeText(_activity.getApplicationContext(), _activity.getString(refMensaje),
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
}
