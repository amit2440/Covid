package com.med.disease.tracking.app.util.otp;

import java.util.Random;

public class OTPGenerator{
	public static String generate() {
		// Generate random integers in range 0 to 9999
//		return String.format("%04d", new Random().nextInt(10000));
		int val = 0;
	    Random r = new Random();
	    do {
	        val = 1000 + r.nextInt(9999);
	    } while (String.valueOf(val).contains("0")); // skip randoms containing '0'
	    return val+"";
	}
}