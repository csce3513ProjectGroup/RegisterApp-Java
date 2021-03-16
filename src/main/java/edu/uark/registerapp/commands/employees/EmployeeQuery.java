package edu.uark.registerapp.commands.employees;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Employee;

import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.commands.exceptions.NotFoundException;

@Service
public class EmployeeQuery implements ResultCommandInterface<Employee> {
    @Override
    public Employee execute() {
        // must return employee object - Employee entity = employee entry in repository
            // create employee entity
            // Optional} is primarily intended for use as a method return type where
            // * there is a clear need to represent "no result," and where using {@code null}
            // * is likely to cause errors.
        // Query an employee from the database by its record ID (Use the existing EmployeeRepository.findById() method)
            
        final Optional<EmployeeEntity> employeeObject = this.employeeRepository.findById(this.employeeID);
        
        // Map the employee entity to an Employee object defined in Task #11
        // Return the Employee object
        // If not found then throw a NotFoundException
        if (employeeObject.isPresent()) {
            return new Employee(employeeObject.get());
        }
        else {
            throw new NotFoundException("Employee");
        }       
    }

    // properties - Employee record ID
    private UUID employeeID; // defines employeeID variable and data type for this class
    
    // class get and set methods - WHY?
    public UUID getEmployeeID() {
        return this.employeeID;
    }
    public EmployeeQuery setEmployeeID(final UUID employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    // make employee object
    @Autowired
    private EmployeeRepository employeeRepository;
}