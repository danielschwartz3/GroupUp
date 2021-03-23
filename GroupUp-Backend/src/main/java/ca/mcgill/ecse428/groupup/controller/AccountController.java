package ca.mcgill.ecse428.groupup.controller;

import ca.mcgill.ecse428.groupup.utility.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.groupup.dto.AccountDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Admin;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.AccountService;

@CrossOrigin(origins = "*")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = {"/register/newStudent", "/register/newStudent/"})
    public AccountDTO createStudentAccount(@RequestParam("userName") String userName,
                                           @RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("institution") String institution,
                                           @RequestParam("password") String password)
            throws IllegalArgumentException {
        Account acc = accountService.createStudentAccount(new Student(), userName, name, email, institution, password);
        return DTOUtil.convertToDTO(acc);
    }

    @PostMapping(value = {"/register/newAdmin", "/register/newAdmin/"})
    public AccountDTO createAdminAccount(@RequestParam("userName") String userName,
                                         @RequestParam("name") String name,
                                         @RequestParam("email") String email,
                                         @RequestParam("institution") String institution,
                                         @RequestParam("password") String password)
            throws IllegalArgumentException {
        Account acc = accountService.createAdminAccount(new Admin(), userName, name, email, institution, password);
        return DTOUtil.convertToDTO(acc);
    }

    //This method allows users to change all information except their password
    @PutMapping(value ={"/account/update", "/account/update/"})
    public AccountDTO changeUserInformation(@RequestParam(value = "email", required = true) String oldEmail,
                                            @RequestParam(value = "newUserName",required = false) String newUserName,
                                            @RequestParam(value = "newName", required = false) String newName,
                                            @RequestParam(value = "newEmail", required = false) String newEmail,
                                            @RequestParam(value = "newInstitution",required = false) String newInstitution)
        throws IllegalArgumentException {
    Account acc = accountService.changeUserInformation(oldEmail, newUserName, newName, newEmail,
                                                    newInstitution);
    return DTOUtil.convertToDTO(acc);
    }

    @PostMapping(value ={"/Login/","/Login/"})
    public AccountDTO LogIn (@RequestParam("email")String email,
                            @RequestParam("password")String password)
                            throws IllegalArgumentException{
     Account acc = accountService.LogIn(email, password);                                  
     return DTOUtil.convertToDTO(acc);
    }
    
    @GetMapping(value = {"/account/{email}", "/account/{email}/"})
    public AccountDTO getAccountByEmail(@PathVariable("email") String email) {
        Account acc = accountService.getAccountByID(email);
        AccountDTO accDTO = DTOUtil.convertToDTO(acc);
        return accDTO;
    }
}