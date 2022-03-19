package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

  @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
  Optional<User> findById(String id);

}
