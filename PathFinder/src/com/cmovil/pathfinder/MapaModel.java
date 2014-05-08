package com.cmovil.pathfinder;

import java.util.ArrayList;

public class MapaModel {

	private MapaActivity _activity;
	private ArrayList<PuntoDeRecorrido> _recorrido;
	
	public MapaModel(MapaActivity a) {

		this._activity = a;
		this._recorrido = (ArrayList<PuntoDeRecorrido>) _activity.getIntent().getSerializableExtra("recorrido");
	}

	public ArrayList<PuntoDeRecorrido> getRecorrido() {
		return _recorrido;
	}
	
	public boolean recorridoVacio() {
		return _recorrido == null || _recorrido.isEmpty();
	}
	
	public PuntoDeRecorrido getPuntoMasReciente() {
		return _recorrido.get(_recorrido.size() - 1);
	}

}
