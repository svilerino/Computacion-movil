package com.gcmovil.gcdtesting.output;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcdtesting.R;
import com.gcmovil.gcdtesting.WellKnownKeys;

public class OutputView extends View{

	private Activity activity;
	private TextView txtGCD;
	
	public OutputView(ActivityOutput activityInput) {
		super(activityInput, null);
		//link references to parameters and fields
		activity = activityInput;
		activity.setContentView(R.layout.activity_output);
		
		//create references to view
		txtGCD = (TextView) activity.findViewById(R.id.txtGCD);		
	}

	public Activity getActivity() {
		return activity;
	}

	public void showResult(Integer numberA, Integer numberB, Integer gcdResult) {
		String prefix = WellKnownKeys.RESULT_PREFIX_TEXT;
		prefix = prefix.replaceAll("_a_", numberA.toString());
		prefix = prefix.replaceAll("_b_", numberB.toString());
		txtGCD.setText(prefix + gcdResult.toString());
	}
	
	public void showMessage(String msg){
		Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
	}

}
