package ca.mcgill.ecse428.groupup.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.groupup.dao.ReactionRepository;
import ca.mcgill.ecse428.groupup.model.*;

@Service
public class ReactionService {

  @Autowired
  private ReactionRepository reactionRepository;

  @Transactional
  public void reactToMessage(String reactionType, Account reactor, Message reactionMessage) {
    Boolean wasReacted = false;
    Reaction reaction = reactionRepository.findByReactorAndReactionMessage(reactor, reactionMessage);
    if(reaction == null){
      reaction = new Reaction();
    }
    for (ReactionType rt : ReactionType.values()) {
      if (reactionType.equals(rt.toString())) {
        reaction.setReactionType(rt);
        wasReacted = true;
      }
    }
    if (!wasReacted) {
      throw new IllegalArgumentException("Unable to react to this message.");
    }
    reaction.setReactionDate(new Date(System.currentTimeMillis()));
    reaction.setReactionMessage(reactionMessage);
    reaction.setReactor(reactor);
    reactionRepository.save(reaction);
    return;
  }

  @Transactional
  public void unReactToMessage(Account reactor, Message reactionMessage) {
    Reaction reaction = reactionRepository.findByReactorAndReactionMessage(reactor, reactionMessage);
    if (reaction == null) {
      throw new IllegalArgumentException("You have not reacted to this message.");
    } else {
      reactionRepository.deleteById(reaction.getId());
    }
    return;
  }

  @Transactional
  public List<Account> getReactorsToMessage(Message reactionMessage) {
    List<Reaction> allReactions =
        toList(reactionRepository.findAllByReactionMessage(reactionMessage));
    List<Account> allReactors = new ArrayList<>();
    for (Reaction r : allReactions) {
      allReactors.add(r.getReactor());
    }
    return allReactors;
  }

  @Transactional
  public List<Reaction> getAllReactionsToMessage(Message reactionMessage) {
    List<Reaction> allReactions =
        toList(reactionRepository.findAllByReactionMessage(reactionMessage));
    return allReactions;
  }

  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }

}
