package com.cmovil.pathfinder;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaView {

	private MapaActivity _activity;
	
	private Button _botonCompartirGmail;
	private Button _botonCompartirTwitter;
	private GoogleMap _mapa;
	
	@SuppressLint("NewApi")
	public MapaView(MapaActivity a) {

		this._activity = a;

		this._botonCompartirGmail = (Button) _activity.findViewById(R.id.botonCompartirGmailM);
		this._botonCompartirTwitter = (Button) _activity.findViewById(R.id.botonCompartirTwitterM);
		this._mapa = ((MapFragment) _activity.getFragmentManager().findFragmentById(R.id.map)).getMap();
	}

	public void mostrarAvisoRecorridoVacio() {
		
		Toast toast = Toast.makeText(_activity.getApplicationContext(), _activity.getString(R.string.msj_recorrido_vacio),
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	public void mapearRecorrido(ArrayList<PuntoDeRecorrido> ps) {
		
		PolylineOptions recorrido = new PolylineOptions()
											.width(5)
											.color(Color.GREEN);
		
		PuntoDeRecorrido p = null;
		for(int i = 0; i < ps.size(); ++i) {
			p = ps.get(i);
			
			recorrido = recorrido.add(new LatLng(p.getLat(), p.getLng()));
			
			if(i < ps.size() - 1)
				this._mapa.addMarker(new MarkerOptions()
	    			.position(new LatLng(p.getLat(), p.getLng()))
	    			.snippet(p.getTag())
	    			.title((new Integer(i + 1)).toString()))
	    			.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
			else
				this._mapa.addMarker(new MarkerOptions()
    			.position(new LatLng(p.getLat(), p.getLng()))
    			.snippet(p.getTag())
    			.title((new Integer(i + 1)).toString()));
		}
		
		this._mapa.addPolyline(recorrido);
		this._mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(p.getLat(), p.getLng()), 16));
	}

}
