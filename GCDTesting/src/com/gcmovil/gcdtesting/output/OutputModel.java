package com.gcmovil.gcdtesting.output;

import com.gcmovil.gcdtesting.WellKnownKeys;

public class OutputModel {
	public Integer calculateGCD(Integer a, Integer b) throws Exception {
		if(isNullOrNegative(a) || isNullOrNegative(b)){
			throw new Exception(WellKnownKeys.NULLORNEGATIVENUMBER_ERR_MSG);
		}else{
			return makeGcd(a, b);
		}
	}
	
	/**
	 * Algoritmo de euclides para calcular el MCD
	 * @param a
	 * @param b
	 * @return
	 */
	public int makeGcd(int a, int b) {
		if(b==0){
			return a;
		}else{
			return makeGcd(b, a % b);
		}
	}
	
	private boolean isNullOrNegative(Integer a) {
		return ( a<=0 );
	}
}
