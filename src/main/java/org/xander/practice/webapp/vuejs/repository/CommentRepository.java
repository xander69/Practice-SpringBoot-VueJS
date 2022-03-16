package org.xander.practice.webapp.vuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xander.practice.webapp.vuejs.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
