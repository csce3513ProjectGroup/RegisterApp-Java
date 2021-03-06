package edu.uark.registerapp.commands.employees;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.UnauthorizedException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;

@Service
public class EmployeeSignInCommand implements ResultCommandInterface<Employee> { 
    @Override
	public Employee execute() {
		this.validateProperties();
		return new Employee(this.SignInEmployee());
	}

	// Validate the incoming Employee request object
	private void validateProperties() {
		// Employee ID should not be blank
		if (StringUtils.isBlank(this.employeeSignIn.getEmployeeId())) {
			throw new UnprocessableEntityException("employee ID");
		}

		// TD: Employee ID should be a number
		try {
			Integer.parseInt(this.employeeSignIn.getEmployeeId());
		} catch (final NumberFormatException e) {
			throw new UnprocessableEntityException("employee ID");
		}

		// Password should not be blank
		if (StringUtils.isBlank(this.employeeSignIn.getPassword())) {
			throw new UnprocessableEntityException("password");
		}
	}

	@Transactional
	private EmployeeEntity SignInEmployee() {
		// Query the employee by the employee ID
            // Use EmployeeRepository.queryByEmployeeID()
		final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findByEmployeeId(
				Integer.parseInt(this.employeeSignIn.getEmployeeId()));

		// verify that the employee exists
		// verify that the password provided matches the database value
			// not using Arrays.equal() since pulling in as strings
		if (!employeeEntity.isPresent() 
			|| !(employeeEntity.get().getPassword().equals(this.employeeSignIn.getPassword()))) {
			throw new UnauthorizedException();
		}
		// Query the activeuser table for a record with the given employee ID		
		final Optional<ActiveUserEntity> activeUserEntity =
			this.activeUserRepository.findByEmployeeId(employeeEntity.get().getId());

		// if an activeuser record already exists
			// Update the record's sessionKey property with the provided session key
			// Use the existing ActiveUserRepository.save() method
				// ActiveUserRepository extends the CrudRepository which provides the .save() method
		if (activeUserEntity.isPresent()) {
			this.activeUserRepository.save(
				activeUserEntity.get().setSessionKey(this.sessionId));
		} else {
			// create an activeuser record in the database
                // set the session key
                // set the necessary employee details
                    // which ones are necessary? - What fields are required by the database? 
                        // ActiveUserEntity.java: id, classification, name, employeeId, sessionKey
                // use the existing ActiveUserRepository.save() method
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
		}
		return employeeEntity.get();
	}

	// Properties: EmployeeSignIn object and Current Session Key
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

	// Create Repository Class Instances
		// create an instance of the class to be used when running the methods
		// above (must be non-static references)
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ActiveUserRepository activeUserRepository;
}
