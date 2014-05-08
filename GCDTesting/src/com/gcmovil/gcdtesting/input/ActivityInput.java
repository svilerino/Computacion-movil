package com.gcmovil.gcdtesting.input;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.gcdtesting.R;

public class ActivityInput extends Activity {

	private InputModel model;
	private InputView view;
	private InputController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//create mvc enviroment
		model = new InputModel();
		view = new InputView(this);
		controller = new InputController(model, view);
		
		//attach listeners
		((EditText)findViewById(R.id.editText1)).addTextChangedListener(controller.getInput1Listener());
		findViewById(R.id.btnGCD).setOnClickListener(controller.getBtnGCDListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_input, menu);
		return true;
	}

}
