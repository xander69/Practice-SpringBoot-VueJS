package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @EntityGraph(attributePaths = {"comments"})
  @Override
  List<Message> findAll();

}
