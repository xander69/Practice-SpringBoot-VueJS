package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.Message;
import org.xander.practice.webapp.vuejs.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @EntityGraph(attributePaths = {"comments"})
  @Override
  List<Message> findAll();

  @EntityGraph(attributePaths = {"comments"})
  Page<Message> findAll(Pageable pageable);

  @EntityGraph(attributePaths = {"comments"})
  Page<Message> findByAuthorIn(List<User> authors, Pageable pageable);
}
