package ca.mcgill.ecse428.groupup.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
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
	
    
	@Test
	@Transactional
	void testCreateAccount() {
		Account newAccount = createSampleAccount();
		Account savedAccount = accountRepository.save(newAccount);
		assertNotNull(savedAccount);
		checkAccountEqual(newAccount, savedAccount);
	}
	
	void checkAccountEqual(Account account1, Account account2) {
		assertEquals(account1.getEmail(), account2.getEmail());
		assertEquals(account1.getInstitution(), account2.getInstitution());
		assertEquals(account1.getName(), account2.getName());
		assertEquals(account1.getPassword(), account2.getPassword());
		assertEquals(account1.getUserName(), account2.getUserName());
	}
	
	@Test
	@Transactional
	void testFindByEmail() {
		Account newAccount = createSampleAccount();
		accountRepository.save(newAccount);
		Account savedAccount = accountRepository.findById(EMAIL).orElse(null);
		assertNotNull(savedAccount);
		checkAccountEqual(newAccount, savedAccount);
	}
	
	@Test
	@Transactional
	void testDeleteAccount() {
		Account newAccount = createSampleAccount();
		Account savedAccount = accountRepository.save(newAccount);
		accountRepository.delete(savedAccount);
		savedAccount = accountRepository.findById(EMAIL).orElse(null);
		assertNull(savedAccount);
	}
	
    Account createSampleAccount(){
		 Account account = new Account();
		 account.setUserName(USERNAME);
		 account.setName(NAME);
		 account.setInstitution(INSTITUTION);
		 account.setEmail(EMAIL);
		 account.setPassword(PASSWORD);
		 return account;
	}
}
