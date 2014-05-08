package com.cmovil.pathfinder;

import android.view.View;
import android.widget.Button;

public class InicioView {

	private InicioActivity _activity;

	private Button _botonIngresar;
	private Button _botonActivar;

	public InicioView(InicioActivity a) {
		
		this._activity = a;

		this._botonIngresar = (Button) _activity.findViewById(R.id.botonIngresarI);
		this._botonActivar = (Button) _activity.findViewById(R.id.botonActivarI);
	}

	public void deshabilitarActivar() {
		
		this._botonActivar.setVisibility(View.INVISIBLE);
	}

}
