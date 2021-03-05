package ca.mcgill.ecse428.groupup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Student;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long>{
	
	List<Chat> findAllByMembers(Student student);
}
