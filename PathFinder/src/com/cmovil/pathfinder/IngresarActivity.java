package com.cmovil.pathfinder;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class IngresarActivity extends Activity {

	private IngresarModel _model;
	private IngresarView _view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingresar);
		setupActionBar();

		_model = new IngresarModel(this);
		_view = new IngresarView(this);

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
		getMenuInflater().inflate(R.menu.ingresar, menu);
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
		
		findViewById(R.id.botonIngresarG).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				esconderTecladoVirtual();
				ingresar();
			}
		});

		((EditText)findViewById(R.id.campoContrasenaG)).setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        if (actionId == EditorInfo.IME_ACTION_DONE) {
		        	esconderTecladoVirtual();
					ingresar();
		        }
		        return false;
		    }
		});
	}

	protected void ingresar() {
		
		_view.activarModoEspera();
		
		_model.guardarDatos(_view.getEmail(), _view.getContrasena());
		
		if (!_model.sonDatosValidos()) {

			_view.mostrarAviso(R.string.formulario_invalido_titulo_g, R.string.formulario_invalido_msj_g);
			_view.desactivarModoEspera();
			return;
		}

		_model.descargarRecorrido();
	}

	protected void onDescargarRecorridoError(int refMsj) {

		_view.mostrarAviso(R.string.descargar_recorrido_error_titulo, refMsj);
		_view.desactivarModoEspera();
	}

	protected void onDescargarRecorridoExito() {
		
		Intent i = new Intent(this, MapaActivity.class);
		i.putExtra("recorrido", (ArrayList<PuntoDeRecorrido>) _model.getRecorrido());
		startActivity(i);
		_view.desactivarModoEspera();
	}

	protected void esconderTecladoVirtual() {

		InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE); 

		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                   InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public IngresarModel getModel() {
		return this._model;
	}

	public IngresarView getView() {
		return this._view;
	}

}
