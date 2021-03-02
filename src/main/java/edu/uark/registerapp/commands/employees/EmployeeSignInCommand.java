package edu.uark.registerapp.commands.employees;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;

@Service
public class EmployeeSignInCommand { // implements VoidCommandInterface {
    // Properties: EmployeeSignIn object and Current Session Key
        // where do I get session key?
    
    @Transactional
    // @Override
    public void execute(final int employeeId, final String password) {
        // TODO: PARAMETERS NEED TO BE CHANGED TO REFLECT INCOMING VALUE AS EMPLOYEE OBJECT -KR
        final String employeeIdAString = Integer.toString(employeeId);
        
        // Validate the incoming Employee request object
            // Employee ID should not be blank
            // TODO: Employee ID should be a number
            // Password should not be blank
        if (employeeIdAString.isBlank()) {
            throw new UnprocessableEntityException("Employee ID");
        }
        else if (password.isBlank()) {
            throw new UnprocessableEntityException("Password");
        }
        // Query the employee by the employee ID
            // Use EmployeeRepository.queryByEmployeeID()
        final Optional <EmployeeEntity> employee = this.employeeRepository.findByEmployeeId(employeeId);
            
            // verify that the employee exists
            // verify that the password provided matches the database value
                // use Arrays.equal() -- TODO: Access the password stored in the entity.
        // if (!employee.isPresent() || !Arrays.equals(password, )) {

        // }
        // Query the activeuser table for a record with the given employee ID
            // EmployeeRepository again I think but what method?
            // Use @Transactional annotation on method
            // if an activeuser record already exists
                // Update the record's sessionKey property with the provided session key
                // Use the existing ActiveUserRepository.save() method
                    // ActiveUserRepository extends the CrudRepository which provides the .save() method
            // else {
                // create an activeuser record in the database
                // set the session key
                // set the necessary employee details
                    // which ones are necessary? - What fields are required by the database? 
                        // ActiveUserEntity.java: id, classification, name, employeeId, sessionKey
                // use the existing ActiveUserRepository.save() method
            //}
    }

    // Properties (Based on Product Codes)
	private String sessionKey;

	public String getSessionKey() {
		return this.sessionKey;
	}

	public EmployeeSignInCommand setSessionKey(final String sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}

// Create Repository Class Instances
    // create an instance of the class to be used when running the methods
    // above (must be non-static references)
    @Autowired
    private EmployeeRepository employeeRepository;   
    @Autowired
    private ActiveUserRepository activeUserRepository;    
}
