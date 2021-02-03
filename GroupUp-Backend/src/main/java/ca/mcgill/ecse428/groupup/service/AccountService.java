package ca.mcgill.ecse428.groupup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.groupup.dao.AccountRepository;
import ca.mcgill.ecse428.groupup.model.*;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository accRepo;

    public Account  createAccount(UserRole role, String username, String student_name,
                                 String user_email, String user_institution, String password)
                                 {   
        Account acc;
        String error = "";
        Boolean isEmailValid = false;
        if(role == null) error += "User's role cannot be empty";
        if(username == null || username.trim().length()==0) error += "Username cannot be empty";
        if(student_name == null || student_name.trim().length()==0) error += "User's full-name cannot be empty";
        if(user_email == null || user_email.trim().length()==0) error += "User's email cannot be empty";
        if(user_institution == null ||user_institution.trim().length()==0) error += "User institution cannot be empty";
        if(password == null || password.trim().length()==0) error += "Password cannot be empty";
        
        //verify email
        int posOfdomain = user_email.indexOf('@');
        String domain = user_email.substring(posOfdomain);
        for (EmailDomain d: EmailDomain.values()){
            if(d.getDomain().equals(domain)){
                isEmailValid = true;
            }
        }
        if(!isEmailValid) error+= "INVALID_EMAIL";
        if(accRepo.existsById(username))error = error + "Already registered";

        acc = new Account();
        acc.setUsername(username);
        acc.setFullName(student_name);
        acc.setEmail(user_email);
        acc.setInstitution(user_institution);
        acc.setPassword(password);
        acc.setUserRole(role);

        accRepo.save(acc);

        return acc;
    }
}
