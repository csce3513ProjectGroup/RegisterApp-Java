package edu.uark.registerapp.models.api;

import org.apache.commons.lang3.StringUtils;

public class EmployeeSignIn {
    private String employeeId;
    private String password;

    public EmployeeSignIn() {
        // VARIANCE: Might need to use StringUtils.Empty instead of ""
        // this.password = "";
        // this.employeeId = "";
        this.password = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
    }

    // get methods
    public String getPassword() {
        return this.password;
    }
    public String getEmployeeId() {
        return this.employeeId;
    }

    // set methods
    public EmployeeSignIn setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
		return this;
    }

    public EmployeeSignIn setPassword(final String password) {
        this.password = password;
		return this;
    }
}
