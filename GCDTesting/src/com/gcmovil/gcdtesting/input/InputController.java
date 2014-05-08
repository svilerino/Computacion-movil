package com.gcmovil.gcdtesting.input;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;

import com.gcmovil.gcdtesting.WellKnownKeys;
import com.gcmovil.gcdtesting.output.ActivityOutput;

public class InputController {

	private InputView inputView;
	private Input1Listener input1Listener;
	private BtnGCDListener btnGCDListener;
	private InputModel inputModel;

	public InputController(InputModel model, InputView view) {
		this.inputView = view;
		this.inputModel = model;
		this.btnGCDListener = new BtnGCDListener();
		this.input1Listener = new Input1Listener();
		
		//inicialmente no hay ningun numero ingresado
		inputView.changeTxt2Status(false);
	}
	
	private void displayResultInOutputActivity(Integer a, Integer b) {
		//get reference from view
		Activity activity = inputView.getActivity();
		//launch activity with result
		Intent intent = new Intent(activity, ActivityOutput.class);
		intent.putExtra(WellKnownKeys.NUMBER_A_EXTRAS_KEY, a);
		intent.putExtra(WellKnownKeys.NUMBER_B_EXTRAS_KEY, b);
		activity.startActivity(intent);
	}

	public InputView getInputView() {
		return inputView;
	}

	public Input1Listener getInput1Listener() {
		return input1Listener;
	}

	public BtnGCDListener getBtnGCDListener() {
		return btnGCDListener;
	}
	
	public class BtnGCDListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			calculateGCD();
		}
	}
	
	private boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	private Integer validateString(String strVal) throws Exception {			
		if(isNumeric(strVal)){
			return Integer.parseInt(strVal);
		}else{
			throw new Exception(WellKnownKeys.NOT_NUMERIC_ERR);
		}
	}
	
	private void calculateGCD() {
		try {
			//retrieve values from view
			String aStr = inputView.getText1();
			String bStr = inputView.getText2();
			
			//validation rules are in the controller, it will throw exception for any error with a suitable message to show in the catch block
			Integer a = validateString(aStr);
			Integer b = validateString(bStr);
			
			//lanzar la otra actividad para calcular y mostrar el resultado
			displayResultInOutputActivity(a, b);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			inputView.showMessage(e.getMessage());
		}			
	}


	public class Input1Listener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable arg0) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			inputView.changeTxt2Status(!inputView.isTxt1Empty());
		}

	}
	
}
