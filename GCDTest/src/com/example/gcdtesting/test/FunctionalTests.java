package com.example.gcdtesting.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gcmovil.gcdtesting.WellKnownKeys;
import com.gcmovil.gcdtesting.input.ActivityInput;
import com.gcmovil.gcdtesting.output.ActivityOutput;

public class FunctionalTests extends ActivityInstrumentationTestCase2<ActivityInput> {

	private static final int WAIT = 2000;
	private static final Integer TEST_NUMBER_A = 56;
	private static final Integer TEST_NUMBER_B = 12;
	private static final int TEST_RESULT = 4;
	private ActivityInput activity;

	public FunctionalTests() {
		super(ActivityInput.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}

	/**
		â€“ Layout 
			â€¢ Los campos de entrada estÃ¡n uno al lado del otro 
			â€¢ El botÃ³n GCD estÃ¡ debajo y centrado 
	 */
	public void testLayout() {
		// existen los 2 textfields y el boton
		EditText text1 = (EditText) activity
				.findViewById(com.example.gcdtesting.R.id.editText1);
		assertNotNull("text1 does not exists", text1);

		EditText text2 = (EditText) activity
				.findViewById(com.example.gcdtesting.R.id.editText2);
		assertNotNull("text2 does not exists", text2);

		Button btnGCD = (Button) activity
				.findViewById(com.example.gcdtesting.R.id.btnGCD);
		assertNotNull("button does not exists", btnGCD);

		// los texts estan uno al lado del otro
		// para esto verifico que se encuentren sobre la misma linea en el eje Y
		// y que la diferencia entre (left+width)de txt1 y left del txt2 sea
		// cercano a 0.
		int[] locationText1 = new int[2];
		int[] locationText2 = new int[2];
		text1.getLocationOnScreen(locationText1);
		text2.getLocationOnScreen(locationText2);
		
		assertEquals("Alineacion de textfields uno al lado del otro eje Y",
				locationText2[1]/*top*/, locationText2[1]/*top*/);
		assertTrue("Alineacion de textfields uno al lado del otro eje X",
				(locationText2[0]/*left*/ - (locationText1[0]/*left*/ + text1.getWidth())) == 0);
		// el boton GCD esta abajo y centrado
		// (abajo) para esto verifico que el boton este en una linea "inferior"
		// sobre el eje Y que alguno de los 2 textfields(estan a la misma
		// altura)
		int[] locationButton = new int[2];
		btnGCD.getLocationOnScreen(locationButton);
		assertTrue(
				"El boton debe estar abajo de los textfields",
				(locationButton[1]/*top*/ > locationText1[1]/*top*/)
						&& (locationButton[1]/*top*/ > locationText2[1]/*top*/));
		// (centrado) verifico que el ancho del boton sea igual a la suma del
		// ancho de los dos textfields y la linea de posicion en el eje X
		// coincida con el txt1
		assertEquals("Boton no centrado", btnGCD.getWidth(), text1.getWidth() + text2.getWidth());
		assertEquals("Boton no centrado", locationButton[0]/*left*/, locationText1[0]/*left*/);
	}	
	
	/**
	 	â€“â€¯Comportamiento 
			â€¢â€¯ La actividad â€œActivityOutputâ€� se lanza correctamente

	 	â€“â€¯Funcionalidad 
		  	â€¢â€¯ Los nÃºmeros son enteros positivos : cambiar TEST_NUMBER_A a un numero negativo y ver que anda el test
	 */
	public void testStartSecondActivity() throws Exception {
		// pongo un monitor a la actividad de output
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				ActivityOutput.class.getName(), null, false);

		// verifico que existen los 2 textfields y el boton
		final EditText text1 = (EditText) activity
				.findViewById(com.example.gcdtesting.R.id.editText1);
		assertNotNull("text1 does not exists", text1);

		final EditText text2 = (EditText) activity
				.findViewById(com.example.gcdtesting.R.id.editText2);
		assertNotNull("text2 does not exists", text2);

		Button btnGCD = (Button) activity
				.findViewById(com.example.gcdtesting.R.id.btnGCD);
		assertNotNull("button does not exists", btnGCD);

		// numeros de prueba
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				text1.setText(TEST_NUMBER_A.toString());
				text2.setText(TEST_NUMBER_B.toString());
			}
			
		});

		// causar click
		// lanza la actividad
		TouchUtils.clickView(this, btnGCD);
		
		// wait for the start of the activity
		ActivityOutput startedActivity = (ActivityOutput) monitor.waitForActivity();
		//La actividad output se inicia correctamente.
		assertNotNull("Error al lanzar ActivityOutput", startedActivity);
		assertEquals("Error al lanzar ActivityOutput", startedActivity.getClass(), ActivityOutput.class);
		
		//leo los numeros pasados por parametro de activityinput a activityoutput
		Integer number1 = startedActivity.getIntent().getExtras().getInt(WellKnownKeys.NUMBER_A_EXTRAS_KEY);
	    Integer number2 = startedActivity.getIntent().getExtras().getInt(WellKnownKeys.NUMBER_B_EXTRAS_KEY);
	    
	    //verifico que los numeros sean positivos
	    assertTrue("Los numeros no son positivos", number1 > 0);
	    assertTrue("Los numeros no son positivos", number2 > 0);
	    
	    //doy tiempo para realizar el calculo
	    try{
	    	Thread.sleep(WAIT);
	    }catch(InterruptedException e){
	    	e.printStackTrace(System.err);
	    }
	    
	    //levanto el contenido del textview resultado de la output
		final TextView textRes = (TextView) startedActivity.findViewById(com.example.gcdtesting.R.id.txtGCD);
		assertNotNull("res txtview does not exists", text1);

		//parseo el resultado
		String res = textRes.getText().toString().split(":")[1].trim();
		
		//testeo correctitud
		assertEquals("El resultado es incorrecto", TEST_RESULT, Integer.parseInt(res));
		
	    // Kill the started activity
        startedActivity.finish();
         
        // Remove idle monitor
        getInstrumentation().removeMonitor(monitor);
	}

}
