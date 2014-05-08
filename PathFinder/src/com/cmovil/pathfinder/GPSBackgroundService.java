package com.cmovil.pathfinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class GPSBackgroundService extends Service implements LocationListener {
	
	//esto deberia estar bien como parametro.
	private static final float THRESHOLD_IN_METERS = 5;
	private static final long THRESHOLD_IN_MILLIS = 2000;

	private String _email;
	private String _contrasena;

	@Override
	public void onCreate() {
		super.onCreate();
		
		// Toast.makeText(this, "Servicio de pathfinder creado", Toast.LENGTH_LONG).show();

		LocationManager locationManager = (LocationManager) getSystemService(GPSBackgroundService.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, THRESHOLD_IN_MILLIS, THRESHOLD_IN_METERS, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, THRESHOLD_IN_MILLIS, THRESHOLD_IN_METERS, this);

		SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
	    
	    Boolean dispositivoActivado = settings.getBoolean("dispositivo_activado", false);
		String email = settings.getString("email", null);
		String contrasena = settings.getString("contrasena", null);

		if(dispositivoActivado && email != null && contrasena != null) {
	    	
			this._email = email;
	    	this._contrasena = contrasena;

	    } else {

			this.stopSelf();
	    }
	}

	@Override
	public void onLocationChanged(Location location) {

		// Toast.makeText(this, "Guardando coordenadas", Toast.LENGTH_LONG).show();

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

		String latitudeStr = new Double(location.getLatitude()).toString();
		String longitudeStr = new Double(location.getLongitude()).toString();
		String currentTime = dateFormat.format(new Date());

		(new EnviarCoordenadasAsyncTask(this))
			.execute(latitudeStr, longitudeStr, currentTime);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// Toast.makeText(this, "GPS apagado...", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		// Toast.makeText(this, "GPS encendido...", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// Toast.makeText(this, "GPS cambio estado..." + status, Toast.LENGTH_LONG).show();
	}

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	public String getEmail() {
		
		return _email;
	}

	public String getContrasena() {
		
		return _contrasena;
	}

}
