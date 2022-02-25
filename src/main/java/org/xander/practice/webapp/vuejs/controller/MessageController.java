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
import org.xander.practice.webapp.vuejs.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping
  @JsonView(Views.IdName.class)
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
  public @ResponseBody
  Message create(@RequestBody Message message) {
    return messageService.addMessage(message);
  }

  @PutMapping("/{id}")
  public @ResponseBody
  Message update(@PathVariable("id") Message messageFromDb,
                 @RequestBody Message message) {
    return messageService.updateMessage(messageFromDb, message);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Message message) {
    messageService.removeMessage(message);
  }
}
