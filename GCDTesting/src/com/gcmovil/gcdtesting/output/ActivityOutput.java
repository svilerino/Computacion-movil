package com.gcmovil.gcdtesting.output;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.gcdtesting.R;

public class ActivityOutput extends Activity {

	private OutputModel model;
	private OutputView view;
	private OutputController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//create mvc enviroment
		model = new OutputModel();
		view = new OutputView(this);
		controller = new OutputController(model, view);			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_output, menu);
		return true;
	}

}
