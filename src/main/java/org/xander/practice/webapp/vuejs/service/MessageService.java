package org.xander.practice.webapp.vuejs.service;

import org.springframework.stereotype.Service;
import org.xander.practice.webapp.vuejs.exception.MessageNotFoundException;
import org.xander.practice.webapp.vuejs.model.MessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

@Service
public class MessageService {

  private Long nextId;
  private List<MessageModel> messages;

  @PostConstruct
  public void init() {
    nextId = 1L;
    messages = new ArrayList<>();
    messages.add(new MessageModel(nextId++, "First message"));
    messages.add(new MessageModel(nextId++, "Second message"));
    messages.add(new MessageModel(nextId++, "Third message"));
  }

  public List<MessageModel> getAllMessages() {
    return messages;
  }

  public MessageModel getMessageById(Long id) {
    return messages.stream()
        .filter(m -> Objects.equals(m.getId(), id))
        .findFirst()
        .orElseThrow(MessageNotFoundException::new);
  }

  public MessageModel addMessage(MessageModel messageModel) {
    messageModel.setId(nextId++);
    messages.add(messageModel);
    return messageModel;
  }

  public MessageModel updateMessage(Long messageId, MessageModel messageModel) {
    MessageModel messageById = getMessageById(messageId);
    messageById.setText(messageModel.getText());
    return messageById;
  }

  public void removeMessage(Long messageId) {
    MessageModel messageModel = getMessageById(messageId);
    messages.remove(messageModel);
  }
}
