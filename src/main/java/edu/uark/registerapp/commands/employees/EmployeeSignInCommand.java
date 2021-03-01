package edu.uark.registerapp.commands.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@Service
public class EmployeeSignInCommand implements VoidCommandInterface {
    // Properties: EmployeeSignIn object and Current Session Key
        // where do I get session key?

    @Override
    public void execute() {

    }

    // Validate the incoming Employee request object
        // Employee ID should not be blank
        // Employee ID should be a number
        // Pasword should not be blank
    
    // Query the employee by the employee ID
        // Use EmployeeRepository.queryByEmployeeID()
        // verify that the employee exists
        // verify that the password provided matches the database value
            // use Arrays.equal()
    
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


// Create Repository Class Instances
    // create an instance of the class to be used when running the methods
    // above (must be non-static references)
    @Autowired
    private EmployeeRepository employeeRepository;   
    @Autowired
    private ActiveUserRepository activeUserRepository;    
}
