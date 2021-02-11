package ca.mcgill.ecse428.groupup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.groupup.model.Account;

@SpringBootTest
public class TestAccountPersistance {
	@Autowired
	AccountRepository accountRepository;
    public static final String USERNAME = "sample_user1";
    public static final String NAME = "John Doe"; //student Full-name
    public static final String EMAIL = "sample_user1@mail.mcgill.ca";
    public static final String INSTITUTION = "Mcgill University";
    public static final String PASSWORD = "samplepassword";
	
    static Account createSampleAccount(){
		 Account account = new Account();
		 account.setUserName(USERNAME);
		 account.setName(NAME);
		 account.setInstitution(INSTITUTION);
		 account.setEmail(EMAIL);
		 account.setPassword(PASSWORD);
		 return account;
	}
}
