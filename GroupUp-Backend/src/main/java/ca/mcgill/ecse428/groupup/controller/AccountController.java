package ca.mcgill.ecse428.groupup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Admin;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.AccountService;

@RestController
public class AccountController{

    @Autowired
    private AccountService accountService;

    @PostMapping(value ={"/register/newStudent","/register/newStudent/"})
    public Account createStudentAccount (@RequestParam("userName")String userName,
                                        @RequestParam("name")String name,
                                        @RequestParam("email")String email,
                                        @RequestParam("institution")String institution,
                                        @RequestParam("password")String password)
                                        throws IllegalArgumentException{
     Account acc = accountService.createStudentAccount(new Student(), userName, name, email, institution, password);                                  
     return acc;
    }

    @PostMapping(value ={"/register/newAdmin","/register/newAdmin/"})
    public Account createAdminAccount (@RequestParam("userName")String userName,
                                        @RequestParam("name")String name,
                                        @RequestParam("email")String email,
                                        @RequestParam("institution")String institution,
                                        @RequestParam("password")String password)
                                        throws IllegalArgumentException{
     Account acc = accountService.createAdminAccount(new Admin(), userName, name, email, institution, password);                                  
     return acc;
    }
  

}