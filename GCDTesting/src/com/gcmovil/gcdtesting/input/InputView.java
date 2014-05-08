package com.gcmovil.gcdtesting.input;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gcdtesting.R;

public class InputView extends View{

	private Activity activity;
	private EditText editTxt1;
	private Button btnGCD;
	private EditText editTxt2;
	
	public InputView(ActivityInput activityInput) {
		super(activityInput, null);
		//link references to parameters and fields
		activity = activityInput;
		activity.setContentView(R.layout.activity_input);
		
		//create references to view
		editTxt1 = (EditText) activity.findViewById(R.id.editText1);
		editTxt2 = (EditText) activity.findViewById(R.id.editText2);
		btnGCD = (Button) activity.findViewById(R.id.btnGCD);
	}

	public void showMessage(String msg){
		Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
	}
	
	public boolean isTxt1Empty(){
		return (editTxt1.getText().length() == 0);
	}

	public void changeTxt2Status(boolean boolValue) {
		editTxt2.setEnabled(boolValue);
		if(!boolValue){
			editTxt2.setText("");
		}
	}

	public String getText1(){
		return editTxt1.getText().toString();
	}
	
	public String getText2(){
		return editTxt2.getText().toString();
	}

	public Activity getActivity() {
		return activity;
	}
	
}
