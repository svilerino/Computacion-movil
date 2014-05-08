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
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

public class ActivarDispositivoAsyncTask extends
		AsyncTask<String, Void, Integer> {

	private String SERVER_ACTIVATE_URL = null;
	private static final String SERVER_ACTIVATE_RESPONSE_CODE = "activate_response_code";
	private static final int SERVER_RESPONSE_CODE_SUCCESS = 0;
	private static final int SERVER_RESPONSE_CODE_EMAIL_TAKEN = 1;
	private static final int SERVER_RESPONSE_CODE_BAD_SYNTAX = 2;
	private static final int SERVER_RESPONSE_CODE_DEVICE_NOT_ACTIVATED = 3;
	private static final int SERVER_RESPONSE_CODE_WRONG_PASSWORD = 4;
	private static final int SERVER_UNKNOWN_ERROR = 5;

	private ActivarActivity _activity;

	public ActivarDispositivoAsyncTask(ActivarActivity a) {
		super();
		this._activity = a;	
		this.SERVER_ACTIVATE_URL = this._activity.getString(R.string.server_base_url) + "/activate";
	}

	@Override
	protected Integer doInBackground(String... params) {
		
		String email = params[0], contrasena = params[1];
		Integer codigoRespuesta = null;

		try {			
			
			HttpResponse httpResponse = enviarHttpPost(email, contrasena);
			
			codigoRespuesta = parsearCodigoRespuesta(httpResponse);
			
        } catch (Exception e) {
        	
            codigoRespuesta = SERVER_UNKNOWN_ERROR;
		}

		return codigoRespuesta;
	}

	@Override
	protected void onPostExecute(Integer codigoRespuesta) {
		
		switch(codigoRespuesta) {
			
			case SERVER_RESPONSE_CODE_SUCCESS:
		 		_activity.onActivarDispositivoExito();
				break;

			case SERVER_RESPONSE_CODE_EMAIL_TAKEN:
				_activity.onActivarDispositivoError(R.string.activar_dispositivo_error_msj_ya_existe);
				break;

			case SERVER_RESPONSE_CODE_BAD_SYNTAX:
				_activity.onActivarDispositivoError(R.string.activar_dispositivo_error_msj_mala_comunicacion);
				break;

			case SERVER_UNKNOWN_ERROR:
			default:
				_activity.onActivarDispositivoError(R.string.activar_dispositivo_error_msj_desconocido);
				break;
		}
	}

	private HttpResponse enviarHttpPost(String email, String contrasena) throws ClientProtocolException, IOException {
		
		AndroidHttpClient client = AndroidHttpClient.newInstance("Awesome User Agent V/1.0");
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 3000);
		HttpConnectionParams.setSoTimeout(client.getParams(), 5000);
		HttpPost post = new HttpPost(this.SERVER_ACTIVATE_URL);
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("email", email));
		pairs.add(new BasicNameValuePair("password", contrasena));
		post.setEntity(new UrlEncodedFormEntity(pairs));
		
		HttpResponse httpResponse = client.execute(post);

		return httpResponse;
	}

	private Integer parsearCodigoRespuesta(HttpResponse httpResponse) throws UnsupportedEncodingException, IllegalStateException, IOException, JSONException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"), 8);
	    StringBuilder sb = new StringBuilder();
	    
		String line = null, response;
	    while ((line = reader.readLine()) != null)
	    	sb.append(line + "\n");
	    response = sb.toString();
	    
	    JSONObject json = new JSONObject(response);

		return json.getInt(SERVER_ACTIVATE_RESPONSE_CODE);
	}
}
