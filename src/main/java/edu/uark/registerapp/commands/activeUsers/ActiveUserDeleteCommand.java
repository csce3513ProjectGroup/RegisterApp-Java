package edu.uark.registerapp.commands.activeUsers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;
import edu.uark.registerapp.commands.exceptions.NotFoundException;

@Service
public class ActiveUserDeleteCommand implements VoidCommandInterface {
    @Transactional
    @Override
    public void execute() {
        //TODO: Validate the incoming Employee request object
            // First and Last Name should not be blank
        // Query the activeuser table for a record with the provided session key
            // use the existing ActiveUserRepository.findBySessionKey() method
            // based on ProductDetailCommand.java
        final Optional<ActiveUserEntity> activeUserRecord = 
            this.activeUserRepository.findBySessionKey(this.sessionKey); // activeUserRecord == activeUserEntity
        
        // Make sure record exists in database
        if (!activeUserRecord.isPresent()) {
            throw new NotFoundException("User");
        }
        else {
            // Delete the activeuser record
                // use the existing ActiveUserRepository.delete() method
            this.activeUserRepository.delete(activeUserRecord.get());
        }
    }

    // Properties: Current sessionKey
    // based on ActiveUserEntity.java and ValidateActiveUserCommand.java
    private String sessionKey;

    public String getSessionKey() {
		return this.sessionKey;
	}

	public ActiveUserDeleteCommand setSessionKey(final String sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}

// Create Instance of Repository Class (to use in method)
    @Autowired
    private ActiveUserRepository activeUserRepository;
}
