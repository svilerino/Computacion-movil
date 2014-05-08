package com.cmovil.pathfinder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ActivarActivity extends Activity {

	private ActivarModel _model;
	private ActivarView _view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activar);
		setupActionBar();

		_model = new ActivarModel(this);
		_view = new ActivarView(this);

		asociarListeners();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void asociarListeners() {
		
		findViewById(R.id.botonActivarA).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				esconderTecladoVirtual();
				activarDispositivo();
			}
		});

		((EditText)findViewById(R.id.campoRepetirContrasenaA)).setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        if (actionId == EditorInfo.IME_ACTION_DONE) {
		        	esconderTecladoVirtual();
					activarDispositivo();
		        }
		        return false;
		    }
		});
	}

	protected void activarDispositivo() {
		
		_view.activarModoEspera();
		
		_model.guardarDatos(_view.getEmail(), _view.getContrasena(), _view.getRepetirContrasena());
		
		if (!_model.sonDatosValidos()) {

			_view.mostrarAviso(R.string.formulario_invalido_titulo_a, R.string.formulario_invalido_msj_a);
			_view.desactivarModoEspera();
			return;
		}

		_model.activarDispositivo();
	}

	public void onActivarDispositivoError(int refMsj) {
		
		_view.mostrarAviso(R.string.activar_dispositivo_error_titulo, refMsj);
		_view.desactivarModoEspera();
	}
	
	public void onActivarDispositivoExito() {
		
		_view.mostrarMensaje(R.string.activar_dispositivo_exito_msj);

		SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();

		editor.putBoolean("dispositivo_activado", true);
		editor.putString("email", _model.getEmail());
		editor.putString("contrasena", _model.getContrasena());
		editor.commit();

		Context context = this.getApplicationContext();
		Intent startServiceIntent = new Intent(context, GPSBackgroundService.class);
        context.startService(startServiceIntent);

		finish();
	}

	public ActivarModel getModel() {
		
		return _model;
	}

	public ActivarView getView() {
		
		return _view;
	}

	protected void esconderTecladoVirtual() {

		InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE); 

		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                   InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
