package edu.uark.registerapp.commands.employees.helpers;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EmployeeHelper {
	public static String padEmployeeId(final int employeeId) {
		final String employeeIdAsString = Integer.toString(employeeId);

		return ((employeeIdAsString.length() < EMPLOYEE_ID_MAXIMUM_LENGTH)
			? StringUtils.leftPad(
				employeeIdAsString,
				EMPLOYEE_ID_MAXIMUM_LENGTH,
				"0")
			: employeeIdAsString);
	}

	public static byte[] hashPassword(final String password) {
		// Hash the password using a MessageDigest. An example can be found at http://tutorials.jenkov.com/java-cryptography/messagedigest.html
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			
			// I don't think it's necessary to do messageDigest.update(password.getBytes());
			// and return messageDigest.digest(); since 
				// 1) there's only one piece of data
				// 2) the .digest() method calls the update() method internally
			// but I could be wrong - if this doesn't work use functional code. -KR
			return messageDigest.digest(password.getBytes());
		}
		catch (NoSuchAlgorithmException e){
			return new byte[0];
		}
	}

	private static final int EMPLOYEE_ID_MAXIMUM_LENGTH = 5;
}
