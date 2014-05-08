package com.cmovil.pathfinder;

import java.io.Serializable;

public class PuntoDeRecorrido implements Serializable {

	private static final long serialVersionUID = 2455455976505179853L;
	private double _lat;
	private double _lng;
	private String _tag;
	
	public PuntoDeRecorrido(double _lat, double _lng, String _tag) {
		super();
		this._lat = _lat;
		this._lng = _lng;
		this._tag = _tag;
	}
	
	public double getLat() {
		return _lat;
	}
	public double getLng() {
		return _lng;
	}
	public String getTag() {
		return _tag;
	}
}
