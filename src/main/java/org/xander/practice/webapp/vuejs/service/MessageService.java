package org.xander.practice.webapp.vuejs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xander.practice.webapp.vuejs.entity.Message;
import org.xander.practice.webapp.vuejs.repository.MessageRepository;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

  private final MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public List<Message> getAllMessages() {
    return messageRepository.findAll();
  }

  public Message addMessage(Message message) {
    message.setCreateDt(new Date());
    message.setChangeDt(new Date());
    return messageRepository.save(message);
  }

  public Message updateMessage(Message messageFromDb, Message message) {
    messageFromDb.setText(message.getText());
    messageFromDb.setChangeDt(new Date());
    return messageRepository.save(messageFromDb);
  }

  public void removeMessage(Message message) {
    messageRepository.delete(message);
  }

  public Message saveMessageFromWebSocket(Message message) {
    message.setChangeDt(new Date());
    return messageRepository.save(message);
  }
}
