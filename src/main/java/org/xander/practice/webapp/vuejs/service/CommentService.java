package org.xander.practice.webapp.vuejs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xander.practice.webapp.vuejs.entity.Comment;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.repository.CommentRepository;

import java.util.Date;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public Comment createComment(Comment comment, User author) {
    comment.setAuthor(author);
    comment.setCreateDt(new Date());
    return commentRepository.save(comment);
  }
}
