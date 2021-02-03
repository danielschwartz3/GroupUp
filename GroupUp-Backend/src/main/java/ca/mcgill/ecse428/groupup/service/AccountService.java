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
        Account acc = new Account();
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
