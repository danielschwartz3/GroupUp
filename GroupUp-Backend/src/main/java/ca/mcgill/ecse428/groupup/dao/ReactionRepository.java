package ca.mcgill.ecse428.groupup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Reaction;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction, Long> {

  Reaction findByReactorAndReactionMessage(Account reactor, Message reactionMessage);
  List<Reaction> findAllByReactionMessage(Message reactionMessage);
  void deleteAllByReactionMessage(Message reactionMessage);

}
