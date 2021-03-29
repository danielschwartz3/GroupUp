package ca.mcgill.ecse428.groupup.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Session;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
  Account findByUserName(String userName);
  Account findBySession(Session session);
  List<Account> findByNameContaining(String name);
}
