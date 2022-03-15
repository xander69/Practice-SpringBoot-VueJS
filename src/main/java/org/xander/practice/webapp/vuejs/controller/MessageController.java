package org.xander.practice.webapp.vuejs.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xander.practice.webapp.vuejs.entity.Message;
import org.xander.practice.webapp.vuejs.entity.Views;
import org.xander.practice.webapp.vuejs.model.EventType;
import org.xander.practice.webapp.vuejs.model.ObjectType;
import org.xander.practice.webapp.vuejs.service.MessageService;
import org.xander.practice.webapp.vuejs.service.WsSender;

import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/api/message")
public class MessageController {

  private final MessageService messageService;
  private final BiConsumer<EventType, Message> wsSender;

  @Autowired
  public MessageController(MessageService messageService,
                           WsSender wsSender) {
    this.messageService = messageService;
    this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullMessage.class);
  }

  @GetMapping
  @JsonView(Views.FullMessage.class)
  public @ResponseBody
  List<Message> list() {
    return messageService.getAllMessages();
  }

  @GetMapping("/{id}")
  @JsonView(Views.FullMessage.class)
  public @ResponseBody
  Message getOne(@PathVariable("id") Message message) {
    return message;
  }

  @PostMapping
  @JsonView(Views.FullMessage.class)
  public @ResponseBody
  Message create(@RequestBody Message message) {
    Message newMessage = messageService.addMessage(message);
    wsSender.accept(EventType.CREATE, newMessage);
    return newMessage;
  }

  @PutMapping("/{id}")
  @JsonView(Views.FullMessage.class)
  public @ResponseBody
  Message update(@PathVariable("id") Message messageFromDb,
                 @RequestBody Message message) {
    Message updatedMessage = messageService.updateMessage(messageFromDb, message);
    wsSender.accept(EventType.UPDATE, updatedMessage);
    return updatedMessage;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Message message) {
    messageService.removeMessage(message);
    wsSender.accept(EventType.REMOVE, message);
  }

//  @MessageMapping("/changeMessage")
//  @SendTo("/topic/activity")
//  public Message change(Message message) {
//    return messageService.saveMessageFromWebSocket(message);
//  }
}
