package org.xander.practice.webapp.vuejs.controller;

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
import org.xander.practice.webapp.vuejs.model.MessageModel;
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
  public @ResponseBody
  List<MessageModel> list() {
    return messageService.getAllMessages();
  }

  @GetMapping("/{id}")
  public @ResponseBody
  MessageModel getOne(@PathVariable Long id) {
    return messageService.getMessageById(id);
  }

  @PostMapping
  public @ResponseBody
  MessageModel create(@RequestBody MessageModel messageModel) {
    return messageService.addMessage(messageModel);
  }

  @PutMapping("/{id}")
  public @ResponseBody
  MessageModel update(@PathVariable Long id,
                      @RequestBody MessageModel messageModel) {
    return messageService.updateMessage(id, messageModel);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    messageService.removeMessage(id);
  }
}
