package ca.mcgill.ecse428.groupup.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.groupup.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
  Account findByUserName(String userName);

  List<Account> findByNameContaining(String name);
}
