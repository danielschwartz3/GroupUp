package ca.mcgill.ecse428.groupup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.AccountService;

@RestController
public class AccountController{

    @Autowired
    private AccountService accountService;

    @PostMapping(value ={"/register/newstudent","/register/newstudent/"})
    public Account createStudentAccount (@RequestParam("user_name")String user_name,
                                        @RequestParam("name")String name,
                                        @RequestParam("email")String email,
                                        @RequestParam("user_institution")String user_institution,
                                        @RequestParam("password")String password)
                                        throws IllegalArgumentException{
     Account acc = accountService.createAccount(new Student(), user_name, name, email, user_institution, password);                                  
     return acc;
    }
  

}