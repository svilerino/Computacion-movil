package com.cmovil.pathfinder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MapaActivity extends Activity {

	private MapaView _view;
	private MapaModel _model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa); // <- aca explota
		setupActionBar();

		_view = new MapaView(this);
		_model= new MapaModel(this);

		asociarListeners();
		
		if(_model.recorridoVacio())
			_view.mostrarAvisoRecorridoVacio();
		else
			_view.mapearRecorrido(_model.getRecorrido());
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
		getMenuInflater().inflate(R.menu.mapa, menu);
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
		
		findViewById(R.id.botonCompartirGmailM).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				compartirViaGmail();
			}
		});
		findViewById(R.id.botonCompartirTwitterM).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				compartirViaTwitter();
			}
		});
	}

	protected void compartirViaGmail() {
		
		if(_model.recorridoVacio()) {
		
			_view.mostrarAvisoRecorridoVacio();
			return;
		}

		PuntoDeRecorrido p = _model.getPuntoMasReciente();
		
		Intent i = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:"));
		i.putExtra(Intent.EXTRA_SUBJECT, "[Via PathFinder] Te mando mi ubicación");
		i.putExtra(Intent.EXTRA_TEXT, "En este link podes ver donde estoy: http://maps.google.com/?q=" + p.getLat() + "," + p.getLng());
		
		startActivity(i);
	}

	protected void compartirViaTwitter() {

		if(_model.recorridoVacio()) {
		
			_view.mostrarAvisoRecorridoVacio();
			return;
		}

		PuntoDeRecorrido p = _model.getPuntoMasReciente();
		
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(new Uri.Builder()
	    	.scheme("https")
	    	.authority("twitter.com")
	    	.path("/intent/tweet")
	    	.appendQueryParameter("source", "webclient")
	    	.appendQueryParameter("text", "[Via #pathfinder] Acá pueden ver donde estoy: http://maps.google.com/?q=" + p.getLat() + "," + p.getLng())
	    	.build());
		
		startActivity(i);
	}

}
