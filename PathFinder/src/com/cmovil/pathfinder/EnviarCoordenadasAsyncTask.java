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

public class EnviarCoordenadasAsyncTask extends
		AsyncTask<String, Void, Integer> {

	private String SERVER_UPLOAD_URL = null;
	private static final String SERVER_UPLOAD_RESPONSE_CODE = "upload_response_code";
	private static final int SERVER_RESPONSE_CODE_SUCCESS = 0;
	private static final int SERVER_RESPONSE_CODE_EMAIL_TAKEN = 1;
	private static final int SERVER_RESPONSE_CODE_BAD_SYNTAX = 2;
	private static final int SERVER_RESPONSE_CODE_DEVICE_NOT_ACTIVATED = 3;
	private static final int SERVER_RESPONSE_CODE_WRONG_PASSWORD = 4;
	private static final int SERVER_UNKNOWN_ERROR = 5;

	private GPSBackgroundService _service;
	
	public EnviarCoordenadasAsyncTask(GPSBackgroundService _service) {
		super();
		
		this._service = _service;
		this.SERVER_UPLOAD_URL = this._service.getString(R.string.server_base_url) + "/upload";
	}

	@Override
	protected Integer doInBackground(String... params) {
		
		String email = this._service.getEmail(), contrasena = this._service.getContrasena();
		String latitud = params[0], longitud = params[1], currentTime = params[2];
		Integer codigoRespuesta = null;
		
		try {			
			
			HttpResponse httpResponse = enviarHttpPost(email, contrasena, latitud, longitud, currentTime);
			
			codigoRespuesta = parsearCodigoRespuesta(httpResponse);
			
        } catch (Exception e) {
        	
            codigoRespuesta = SERVER_UNKNOWN_ERROR;
		}

		return codigoRespuesta;
	}

	@Override
	protected void onPostExecute(Integer codigoRespuesta) {
		
		return;
	}

	private HttpResponse enviarHttpPost(String email, String contrasena, String latitud, String longitud, String currentTime) throws ClientProtocolException, IOException {
		
		AndroidHttpClient client = AndroidHttpClient.newInstance("Awesome User Agent V/1.0");
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 3000);
		HttpConnectionParams.setSoTimeout(client.getParams(), 5000);
		HttpPost post = new HttpPost(this.SERVER_UPLOAD_URL);
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("email", email));
		pairs.add(new BasicNameValuePair("password", contrasena));
		pairs.add(new BasicNameValuePair("latitude", latitud));
		pairs.add(new BasicNameValuePair("longitude", longitud));
		pairs.add(new BasicNameValuePair("tag", currentTime));
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

		return json.getInt(SERVER_UPLOAD_RESPONSE_CODE);
	}
}
