package edu.uark.registerapp.commands.employees;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import javax.transaction.Transactional; // may need this import -KR

import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

// import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;

import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.api.EmployeeSignIn;

import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

import edu.uark.registerapp.commands.exceptions.UnauthorizedException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;

@Service
public class EmployeeSignInCommand implements ResultCommandInterface<Employee> { // implements VoidCommandInterface {
    @Override
	public Employee execute() {
		this.validateProperties();

		return new Employee(this.SignInEmployee());
	}

	// Helper methods
	private void validateProperties() {
		if (StringUtils.isBlank(this.employeeSignIn.getEmployeeId())) {
			throw new UnprocessableEntityException("employee ID");
		}
		try {
			Integer.parseInt(this.employeeSignIn.getEmployeeId());
		} catch (final NumberFormatException e) {
			throw new UnprocessableEntityException("employee ID");
		}
		if (StringUtils.isBlank(this.employeeSignIn.getPassword())) {
			throw new UnprocessableEntityException("password");
		}
	}

	@Transactional
	private EmployeeEntity SignInEmployee() {
		final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findByEmployeeId(
				Integer.parseInt(this.employeeSignIn.getEmployeeId()));

		if (!employeeEntity.isPresent()
			|| !Arrays.equals(
				employeeEntity.get().getPassword(),
				EmployeeHelper.hashPassword(this.employeeSignIn.getPassword()))
		) {

			throw new UnauthorizedException();
		}

		final Optional<ActiveUserEntity> activeUserEntity =
			this.activeUserRepository
				.findByEmployeeId(employeeEntity.get().getId());

		if (!activeUserEntity.isPresent()) {
			this.activeUserRepository.save(
					(new ActiveUserEntity())
						.setSessionKey(this.sessionId)
						.setEmployeeId(employeeEntity.get().getId())
						.setClassification(
							employeeEntity.get().getClassification())
						.setName(
							employeeEntity.get().getFirstName()
								.concat(" ")
								.concat(employeeEntity.get().getLastName())));
		} else {
			this.activeUserRepository.save(
				activeUserEntity.get().setSessionKey(this.sessionId));
		}

		return employeeEntity.get();
	}

	// Properties
	private EmployeeSignIn employeeSignIn;
	public EmployeeSignIn getEmployeeSignIn() {
		return this.employeeSignIn;
	}
	public EmployeeSignInCommand setEmployeeSignIn(final EmployeeSignIn employeeSignIn) {
		this.employeeSignIn = employeeSignIn;
		return this;
	}

	private String sessionId;
	public String getSessionId() {
		return this.sessionId;
	}
	public EmployeeSignInCommand setSessionId(final String sessionId) {
		this.sessionId = sessionId;
		return this;
	}

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ActiveUserRepository activeUserRepository;

//     // Properties: EmployeeSignIn object and Current Session Key
//         // sessionKey is passed to the method when called (I think) -KR
    
//     @Transactional
//     // @Override
//     public void execute(final int employeeId, final String password) {
//         // TODO: PARAMETERS NEED TO BE CHANGED TO REFLECT INCOMING VALUE AS EMPLOYEE OBJECT -KR
//         final String employeeIdAString = Integer.toString(employeeId);
        
//         // Validate the incoming Employee request object
//             // Employee ID should not be blank
//             // TODO: Employee ID should be a number
//             // Password should not be blank
//         if (employeeIdAString.isBlank()) {
//             throw new UnprocessableEntityException("Employee ID");
//         }
//         else if (password.isBlank()) {
//             throw new UnprocessableEntityException("Password");
//         }
//         // Query the employee by the employee ID
//             // Use EmployeeRepository.queryByEmployeeID()
//         final Optional <EmployeeEntity> employee = this.employeeRepository.findByEmployeeId(employeeId);
            
//             // verify that the employee exists
//             // verify that the password provided matches the database value
//                 // use Arrays.equal()
//         if (!employee.isPresent() || !Arrays.equals(EmployeeHelper.hashPassword(password), employee.get().getPassword())) {
//             throw new UnauthorizedException();
//         }
//         else {
//             //sign employee in
//             // signIn(employee);
//         }
//     }
//     private EmployeeEntity signIn(final EmployeeEntity employee) {
//         // Query the activeuser table for a record with the given employee ID
//             // EmployeeRepository again I think but what method?
//             // Use @Transactional annotation on method
//             // if an activeuser record already exists
//                 // Update the record's sessionKey property with the provided session key
//                 // Use the existing ActiveUserRepository.save() method
//                     // ActiveUserRepository extends the CrudRepository which provides the .save() method
//             // else {
//                 // create an activeuser record in the database
//                 // set the session key
//                 // set the necessary employee details
//                     // which ones are necessary? - What fields are required by the database? 
//                         // ActiveUserEntity.java: id, classification, name, employeeId, sessionKey
//                 // use the existing ActiveUserRepository.save() method
//             //}
//         return employee;
//     }

//     // Properties (Based on Product Codes)
// 	private String sessionKey;

// 	public String getSessionKey() {
// 		return this.sessionKey;
// 	}

// 	public EmployeeSignInCommand setSessionKey(final String sessionKey) {
// 		this.sessionKey = sessionKey;
// 		return this;
// 	}

// // Create Repository Class Instances
//     // create an instance of the class to be used when running the methods
//     // above (must be non-static references)
//     @Autowired
//     private EmployeeRepository employeeRepository;   
//     @Autowired
//     private ActiveUserRepository activeUserRepository;    
}
