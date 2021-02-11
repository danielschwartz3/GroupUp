package ca.mcgill.ecse428.groupup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.groupup.dao.AccountRepository;
import ca.mcgill.ecse428.groupup.dao.AdminRepository;
import ca.mcgill.ecse428.groupup.dao.StudentRepository;
import ca.mcgill.ecse428.groupup.model.*;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService {
    
    @Autowired
    AccountRepository accRepo;
    @Autowired
    StudentRepository stuRepo;
    @Autowired
    AdminRepository adminRepo;

    @Transactional
    public Account  createStudentAccount(Student role, String userName, String name,
                                 String email, String institution, String password)
                                 {   
        Account acc;
        String error = "";
        Boolean isEmailValid = false;
        error = verifyInput(role, userName, name, email, institution, password);
        isEmailValid = verifyEmail(email);
        if(!isEmailValid) error+= "INVALID_EMAIL";
        if(accRepo.existsById(email))error = error + "Already registered";
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        acc = new Account();
        acc.setUserName(userName);
        acc.setName(name);
        acc.setEmail(email);
        acc.setInstitution(institution);
        acc.setPassword(password);
        acc.setUserRole(role);
        role.setAccount(acc);
        stuRepo.save(role);
        accRepo.save(acc);
        return acc;
    }
    @Transactional
    public Account  createAdminAccount(Admin role, String userName, String name,
                                 String email, String institution, String password)
                                 {   
        Account acc;
        String error = "";
        Boolean isEmailValid = false;
        error = verifyInput(role, userName, name, email, institution, password);
        isEmailValid = verifyEmail(email);
        if(!isEmailValid) error+= "INVALID_EMAIL";
        if(accRepo.existsById(email))error = error + "Already registered";
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        acc = new Account();
        acc.setUserName(userName);
        acc.setName(name);
        acc.setEmail(email);
        acc.setInstitution(institution);
        acc.setPassword(password);
        acc.setUserRole(role);
        role.setAccount(acc);
        adminRepo.save(role);
        accRepo.save(acc);
        return acc;
    }


    
    @Transactional
    public Account LogIn(String email, String password) throws IllegalArgumentException{
        Account acc = accRepo.findById(email).orElse(null);
        if(acc == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		} else if(!acc.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password is incorrect.");
		}
        return acc;
    }
    
    
   /**
    * Helper methods for service class
    * @param email
    * @return
    */ 
    private Boolean verifyEmail(String email) {
        Boolean isValid = false;
        int offset = 1;
        int posOfdomain = email.indexOf('@');
        String domain = email.substring(posOfdomain + offset);
        for (EmailDomain d: EmailDomain.values()){
            if(d.getDomain().equals(domain)){
                isValid = true;
            }
        }
        return isValid;
    }

    private String verifyInput(UserRole role, String userName, String name, String email, String institution,
            String password) {
            String error = "";
            if(role == null) error += "User's role cannot be empty";
            if(userName == null || userName.trim().length()==0) error += "Username cannot be empty";
            if(name == null || name.trim().length()==0) error += "User's full-name cannot be empty";
            if(email == null || email.trim().length()==0) error += "User's email cannot be empty";
            if(institution == null ||institution.trim().length()==0) error += "User institution cannot be empty";
            if(password == null || password.trim().length()==0) error += "Password cannot be empty";
        return error;
    }
}
