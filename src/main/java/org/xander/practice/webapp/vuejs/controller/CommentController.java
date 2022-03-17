package org.xander.practice.webapp.vuejs.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xander.practice.webapp.vuejs.entity.Comment;
import org.xander.practice.webapp.vuejs.entity.User;
import org.xander.practice.webapp.vuejs.entity.Views;
import org.xander.practice.webapp.vuejs.model.EventType;
import org.xander.practice.webapp.vuejs.model.ObjectType;
import org.xander.practice.webapp.vuejs.service.CommentService;
import org.xander.practice.webapp.vuejs.service.CustomOidcUserService;
import org.xander.practice.webapp.vuejs.service.WsSender;

import java.util.function.BiConsumer;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

  private final CommentService commentService;
  private final CustomOidcUserService userService;
  private final BiConsumer<EventType, Comment> wsSender;

  @Autowired
  public CommentController(CommentService commentService,
                           CustomOidcUserService userService,
                           WsSender wsSender) {
    this.commentService = commentService;
    this.userService = userService;
    this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
  }

  @PostMapping
  @JsonView(Views.FullComment.class)
  public @ResponseBody
  Comment create(@RequestBody Comment comment,
                 KeycloakPrincipal<KeycloakSecurityContext> principal) {
    User user = userService.loadUser(principal.getKeycloakSecurityContext().getToken());
    Comment newComment = commentService.createComment(comment, user);
    wsSender.accept(EventType.CREATE, newComment);
    return newComment;
  }
}
