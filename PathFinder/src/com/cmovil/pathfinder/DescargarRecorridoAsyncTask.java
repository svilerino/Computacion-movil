package com.cmovil.pathfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

public class DescargarRecorridoAsyncTask extends
		AsyncTask<String, Void, DescargarRecorridoAsyncTask.Pair<Integer, ArrayList<PuntoDeRecorrido>>> {

	private String SERVER_DOWNLOAD_URL = null;
	private static final String SERVER_DOWNLOAD_RESPONSE_CODE = "download_response_code";
	private static final String SERVER_DOWNLOAD_DATA_KEY = "data";
	private static final String SERVER_DOWNLOAD_LAT_KEY = "lat";
	private static final String SERVER_DOWNLOAD_LONG_KEY = "long";
	private static final String SERVER_DOWNLOAD_TAG_KEY = "tag";
	private static final int SERVER_RESPONSE_CODE_SUCCESS = 0;
	private static final int SERVER_RESPONSE_CODE_EMAIL_TAKEN = 1;
	private static final int SERVER_RESPONSE_CODE_BAD_SYNTAX = 2;
	private static final int SERVER_RESPONSE_CODE_DEVICE_NOT_ACTIVATED = 3;
	private static final int SERVER_RESPONSE_CODE_WRONG_PASSWORD = 4;
	private static final int SERVER_UNKNOWN_ERROR = 5;

	private IngresarActivity _activity;

	public DescargarRecorridoAsyncTask(IngresarActivity a) {
		super();
		this._activity = a;
		this.SERVER_DOWNLOAD_URL = this._activity.getString(R.string.server_base_url) + "/download";
	}

	@Override
	protected Pair<Integer, ArrayList<PuntoDeRecorrido>> doInBackground(String... params) {
		
		String email = params[0], contrasena = params[1];
		Pair<Integer, ArrayList<PuntoDeRecorrido>> respuesta;

		try {			
			
			HttpResponse httpResponse = enviarHttpPost(email, contrasena);
			
			respuesta = parsearRespuesta(httpResponse);
			
        } catch (Exception e) {
        	
            respuesta = new Pair<Integer, ArrayList<PuntoDeRecorrido>>(SERVER_UNKNOWN_ERROR, null);
		}

		return respuesta;
	}

	@Override
	protected void onPostExecute(Pair<Integer, ArrayList<PuntoDeRecorrido>> respuesta) {

		switch(respuesta.t) {
		case SERVER_RESPONSE_CODE_SUCCESS:
			_activity.getModel().setRecorrido(respuesta.u);
			_activity.onDescargarRecorridoExito();
			break;

		case SERVER_RESPONSE_CODE_BAD_SYNTAX:
			_activity.onDescargarRecorridoError(R.string.descargar_recorrido_error_msj_mala_comunicacion);
			break;
			
		case SERVER_RESPONSE_CODE_DEVICE_NOT_ACTIVATED:
			_activity.onDescargarRecorridoError(R.string.descargar_recorrido_error_msj_no_existe);
			break;
			
		case SERVER_RESPONSE_CODE_WRONG_PASSWORD:
			_activity.onDescargarRecorridoError(R.string.descargar_recorrido_error_msj_contrasena_equivocada);
			break;
			
		case SERVER_UNKNOWN_ERROR:
			_activity.onDescargarRecorridoError(R.string.descargar_recorrido_error_msj_desconocido);
		default:
			break;
		}
	}

	private HttpResponse enviarHttpPost(String email, String contrasena) throws ClientProtocolException, IOException {
		
		AndroidHttpClient client = AndroidHttpClient.newInstance("Awesome User Agent V/1.0");
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 3000);
		HttpConnectionParams.setSoTimeout(client.getParams(), 5000);
		HttpPost post = new HttpPost(this.SERVER_DOWNLOAD_URL);
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("email", email));
		pairs.add(new BasicNameValuePair("password", contrasena));
		post.setEntity(new UrlEncodedFormEntity(pairs));
		
		HttpResponse httpResponse = client.execute(post);

		return httpResponse;
	}

	private Pair<Integer, ArrayList<PuntoDeRecorrido>> parsearRespuesta(HttpResponse httpResponse) throws UnsupportedEncodingException, IllegalStateException, IOException, JSONException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"), 8);
	    StringBuilder sb = new StringBuilder();
	    
		String line = null, response;
	    while ((line = reader.readLine()) != null)
	    	sb.append(line + "\n");
	    response = sb.toString();
	    
		JSONObject json = new JSONObject(response);
		Integer codigoRespuesta = json.getInt(SERVER_DOWNLOAD_RESPONSE_CODE);
		JSONArray datos = json.getJSONArray(SERVER_DOWNLOAD_DATA_KEY);

	    ArrayList<PuntoDeRecorrido> recorrido = new ArrayList<PuntoDeRecorrido>();

        for(int i = 0; i < datos.length(); i++) {

            Double latitud = Double.parseDouble(datos.getJSONObject(i).getString(SERVER_DOWNLOAD_LAT_KEY));
            Double longitud = Double.parseDouble(datos.getJSONObject(i).getString(SERVER_DOWNLOAD_LONG_KEY));
            String tag = datos.getJSONObject(i).getString(SERVER_DOWNLOAD_TAG_KEY);
            recorrido.add(new PuntoDeRecorrido(latitud, longitud, tag));
        }

		return new Pair<Integer, ArrayList<PuntoDeRecorrido>>(codigoRespuesta, recorrido);
	}
	
	static class Pair<T, U> {         
		public final T t;
		public final U u;

		public Pair(T t, U u) {         
			this.t= t;
			this.u= u;
		}
 	}
}