package edu.uark.registerapp.controllers.enums;

public enum ViewModelNames {
	NOT_DEFINED(""),
	ERROR_MESSAGE("errorMessage"),
	IS_ELEVATED_USER("isElevatedUser"),
	PRODUCTS("products"), // Product listing
	PRODUCT("product"), // Product detail
	EMPLOYEE_ID("employeeId"); // Sign in  -- Had to ADD IN FOR SignInRouteController TO WORK -KR
	
	public String getValue() {
		return value;
	}
	
	private String value;

	private ViewModelNames(final String value) {
		this.value = value;
	}
}
