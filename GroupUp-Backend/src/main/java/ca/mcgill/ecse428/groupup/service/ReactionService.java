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
    public Boolean reactToMessage(String reactionType, Student reactor, Message reactionMessage){
        Boolean wasReacted = false;
		Reaction reaction = reactionRepository.findByReactorAndReactionMessage(reactor, reactionMessage);                                     
		for(ReactionType rt : ReactionType.values()){
			if(reactionType.equals(rt.toString())){
				reaction.setReactionType(rt);
                wasReacted = true;
			}
		}
		reaction.setReactionDate(new Date(System.currentTimeMillis()));
		reaction.setReactionMessage(reactionMessage);
		reaction.setReactor(reactor);
		reactionRepository.save(reaction);
		return wasReacted;
	}

    @Transactional
	public Boolean unReactToMessage(Student reactor, Message reactionMessage){
        Boolean wasRemoved = false;
        Reaction reaction = reactionRepository.findByReactorAndReactionMessage(reactor, reactionMessage);
        if(reaction == null){
            wasRemoved = true;
        }else{
            reactionRepository.deleteById(reaction.getId());
            wasRemoved = reactionRepository.existsById(reaction.getId());
        }
        return wasRemoved;
	}

    @Transactional
	public List<Student> getReactorsToMessage(Message reactionMessage){
        List<Reaction> allReactions = toList(reactionRepository.findAllByReactionMessage(reactionMessage));
        List<Student> allReactors = new ArrayList<>();
        for(Reaction r : allReactions){
            allReactors.add(r.getReactor());
        }
        return allReactors;
	}

    @Transactional
    public List<Reaction> getAllReactionsToMessage(Message reactionMessage){
        List<Reaction> allReactions = toList(reactionRepository.findAllByReactionMessage(reactionMessage));
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