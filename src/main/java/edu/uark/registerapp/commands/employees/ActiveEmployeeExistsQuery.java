package edu.uark.registerapp.commands.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class ActiveEmployeeExistsQuery implements VoidCommandInterface {
    @Override
	public void execute() {
    //TODO: Query if any active employees exist in the database
        // use EmployeeRepository.existsByIsActive() method to check if any
        // active employees exist in the database
        if (!this.employeeRepository.existsByIsActive(true)) {
            // throw NotFoundException if no active employee records are in the database
            // create new instance of NotFoundException class (new error)
            throw new NotFoundException("Active Employee");  
        }
  	}

    // create an instance of the EmployeeRepository class to be used when
    // running the execute() method above (must be a non-static reference)
    @Autowired
    private EmployeeRepository employeeRepository;
}
