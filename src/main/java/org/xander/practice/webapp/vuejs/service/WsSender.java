package org.xander.practice.webapp.vuejs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.xander.practice.webapp.vuejs.model.EventType;
import org.xander.practice.webapp.vuejs.model.ObjectType;
import org.xander.practice.webapp.vuejs.model.WsEvent;

import java.util.function.BiConsumer;

@Component
public class WsSender {
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final ObjectMapper objectMapper;

  public WsSender(SimpMessagingTemplate simpMessagingTemplate,
                  ObjectMapper objectMapper) {
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.objectMapper = objectMapper;
  }

  public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class<?> view) {
    ObjectWriter objectWriter = objectMapper
        .setConfig(objectMapper.getSerializationConfig())
        .writerWithView(view);
    return (eventType, payload) -> {
      String value;
      try {
        value = objectWriter.writeValueAsString(payload);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
      simpMessagingTemplate.convertAndSend("/topic/activity",
          new WsEvent(objectType, eventType, value));
    };
  }
}
