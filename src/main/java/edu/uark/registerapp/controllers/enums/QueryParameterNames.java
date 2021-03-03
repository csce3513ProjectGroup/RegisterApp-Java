package edu.uark.registerapp.controllers.enums;

public enum QueryParameterNames {
	NOT_DEFINED(""),
	ERROR_CODE("errorCode"),
	EMPLOYEE_ID("employeeId"); // Had to ADD IN FOR SignInRouteController TO "WORK" -KR

	public String getValue() {
		return value;
	}
	
	private String value;

	private QueryParameterNames(final String value) {
		this.value = value;
	}
}
