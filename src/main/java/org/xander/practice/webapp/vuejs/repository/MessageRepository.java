package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
