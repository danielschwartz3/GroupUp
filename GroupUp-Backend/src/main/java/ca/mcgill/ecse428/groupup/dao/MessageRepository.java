package ca.mcgill.ecse428.groupup.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
  Page<Message> findByLocationAndIdGreaterThan(Chat location, long id, Pageable page);

  Page<Message> findByLocationAndIdLessThan(Chat location, long id, Pageable page);

  Page<Message> findByLocation(Chat location, Pageable page);
}
