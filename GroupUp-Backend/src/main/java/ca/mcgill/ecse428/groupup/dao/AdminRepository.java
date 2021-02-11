package ca.mcgill.ecse428.groupup.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.groupup.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	
}
