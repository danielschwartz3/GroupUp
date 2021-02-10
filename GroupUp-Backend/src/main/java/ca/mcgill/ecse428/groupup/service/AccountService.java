package ca.mcgill.ecse428.groupup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.groupup.dao.AccountRepository;
import ca.mcgill.ecse428.groupup.model.*;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService {
    
    @Autowired
    AccountRepository accRepo;

    @Transactional
    public Account  createAccount(UserRole role, String userName, String name,
                                 String email, String institution, String password)
                                 {   
        Account acc;
        String error = "";
        Boolean isEmailValid = false;
        if(role == null) error += "User's role cannot be empty";
        if(userName == null || userName.trim().length()==0) error += "Username cannot be empty";
        if(name == null || name.trim().length()==0) error += "User's full-name cannot be empty";
        if(email == null || email.trim().length()==0) error += "User's email cannot be empty";
        if(institution == null ||institution.trim().length()==0) error += "User institution cannot be empty";
        if(password == null || password.trim().length()==0) error += "Password cannot be empty";
        
        //verify email
        int posOfdomain = email.indexOf('@');
        String domain = email.substring(posOfdomain);
        for (EmailDomain d: EmailDomain.values()){
            if(d.getDomain().equals(domain)){
                isEmailValid = true;
            }
        }
        if(!isEmailValid) error+= "INVALID_EMAIL";
        if(accRepo.existsById(userName))error = error + "Already registered";

        acc = new Account();
        acc.setUsername(userName);
        acc.setFullName(name);
        acc.setEmail(email);
        acc.setInstitution(institution);
        acc.setPassword(password);
        acc.setUserRole(role);

        accRepo.save(acc);

        return acc;
    }

    @Transactional
    public Account LogIn(String email, String password) throws IllegalArgumentException{
        Account acc = accRepo.findByEmail(email);
        if(acc == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		} else if(!acc.getPassword().equals(password)) {
			throw new IllegalArgumentException("Password is incorrect.");
		}
        return acc;
    }



}
