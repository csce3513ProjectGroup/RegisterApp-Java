package edu.uark.registerapp.commands.employees;

// might not need the list imports - KR
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.activeUsers.*;

import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class ActiveEmployeeExistsQuery {
    //TODO: Query if any active employees exist in the database
    public boolean padEmployeeId() {
        //use EmployeeRepository.existsByIsActive() method to
        //check if any active employees exist in the database
        // throw NotFoundException if no active employee records are in the database
    	//if (EmployeeRepository.existsByIsActive()) {
    		
    }

}
