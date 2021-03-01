package ca.mcgill.ecse428.groupup.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{
	Page<Message> findByLocation( Pageable pageable, Chat location);
}
