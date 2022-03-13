package org.xander.practice.webapp.vuejs.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.xander.practice.webapp.vuejs.entity.Views;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEvent {
  private ObjectType objectType;
  private EventType eventType;
  @JsonRawValue
  private String body;
}
