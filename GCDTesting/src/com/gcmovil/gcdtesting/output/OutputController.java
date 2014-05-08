package com.gcmovil.gcdtesting.output;

import android.os.AsyncTask;

import com.gcmovil.gcdtesting.WellKnownKeys;

public class OutputController {

	private OutputView outputView;
	private Integer numberA;
	private Integer numberB;
	private OutputModel outputModel;

	public OutputController(OutputModel model, OutputView view) {
		this.outputView = view;
		this.outputModel = model;
	
		//obtener los parametros
		numberA = outputView.getActivity().getIntent().getExtras().getInt(WellKnownKeys.NUMBER_A_EXTRAS_KEY);
		numberB = outputView.getActivity().getIntent().getExtras().getInt(WellKnownKeys.NUMBER_B_EXTRAS_KEY);
		
		//calcular gcd y mostrarlo
		new AsyncGCD().execute(numberA, numberB);
	}
	
	private class AsyncGCD extends AsyncTask<Integer, Void, Integer>{

		private Integer numberA;
		private Integer numberB;

		@Override
		protected Integer doInBackground(Integer... params) {
			numberA = params[0];
			numberB = params[1];
			//calculate gcd in the model, any error will throw exception from model. ie. Negative or null numbers
			try {
				Integer result = outputModel.calculateGCD(numberA, numberB);
				return result;
			} catch (Exception e) {
				e.printStackTrace(System.err);
				outputView.showMessage(e.getMessage());
				return null;
			}
		}
		
		protected void onPostExecute(Integer result) {
			if(result!=null){
				outputView.showResult(numberA, numberB, result);				
			}
		}
		
	}

}
