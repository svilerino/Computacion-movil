package com.cmovil.pathfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class InicioActivity extends Activity {

	private InicioView _view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);

		_view = new InicioView(this);

		asociarListeners();
	}

	@Override
	protected void onResume() {
		super.onResume();

		SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
	    Boolean dispositivoActivado = settings.getBoolean("dispositivo_activado", false);

	    if(dispositivoActivado)
	    	_view.deshabilitarActivar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true;
	}

	private void asociarListeners() {
		
		findViewById(R.id.botonIngresarI).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				redirigirIngresarActivity();
			}
		});
		findViewById(R.id.botonActivarI).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				redirigirActivarActivity();
			}
		});
	}

	protected void redirigirIngresarActivity() {

		startActivity(new Intent(this, IngresarActivity.class));
	}

	protected void redirigirActivarActivity() {
		
		startActivity(new Intent(this, ActivarActivity.class));
	}

}
