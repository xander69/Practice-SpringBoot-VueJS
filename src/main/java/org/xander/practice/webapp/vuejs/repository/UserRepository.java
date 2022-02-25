package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
