package ca.mcgill.ecse428.groupup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse428.groupup.model.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, Integer> {
  List<Session> findAll();
}
