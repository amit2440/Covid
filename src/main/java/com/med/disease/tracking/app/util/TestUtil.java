package com.med.disease.tracking.app.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestUtil {

	public static void main(String[] args) throws InstantiationException {
		// TODO Auto-generated method stub
		TestUtil tu = new TestUtil();

//		tu.testMobileNumber();
//		tu.testName();
//		tu.alphaNumeric();
		try {
			tu.testmthodEx("com.med.disease.tracking.app.util.TestUtil", "testMobileNumber");
		} catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testMobileNumber() {
		String regx = "(\\+91)?(-)?\\s*?(91)?\\s*?(\\d{3})-?\\s*?(\\d{3})-?\\s*?(\\d{4})";

		String mobileNumber = "+919756432848";

		String ss = "";

		if (mobileNumber.matches(regx)) {
			ss = "VALID MOBILE NUMBER";
		} else {
			ss = "INVALID MOBILE NUMBER";
		}

		System.out.println("ss value --> " + ss);
	}

	public void testName() {
//		String regx = "[a-zA-Z]{3,30}";
		String regx = "^([a-zA-Z]{2,}\\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";

		String mobileNumber = "viendra dsds";

		String ss = "";

		if (mobileNumber.matches(regx)) {
			ss = "VALID MOBILE NUMBER";
		} else {
			ss = "INVALID MOBILE NUMBER";
		}

		System.out.println("ss value --> " + ss);
	}

	public void alphaNumeric() {

		String regx = "^[a-zA-Z0-9_]*$";

		String mobileNumber = "vien123";

		String ss = "";

		if (mobileNumber.matches(regx)) {
			ss = "VALID MOBILE NUMBER";
		} else {
			ss = "INVALID MOBILE NUMBER";
		}

		System.out.println("ss value --> " + ss);
	}
	
	public final void testmthodEx(String className, String methodName) throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class classObj = Class.forName(className);
		Object ref = classObj.newInstance();
//		Method[] publicMethods = Class.forName("com.med.disease.tracking.app.util.TestUtil").getMethods();
		Method[] publicMethods = Class.forName(className).getMethods();
		for (Method m : publicMethods) {
			if(m.getName().equals(methodName)) {
				System.out.println("GOT METHOD");
				m.invoke(ref, null);
			}
		}
		
		
	}
	
	public void testmethod() {
		System.out.println("executing test method.....");
	}
}
