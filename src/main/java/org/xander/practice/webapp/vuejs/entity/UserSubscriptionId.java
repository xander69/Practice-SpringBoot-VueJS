package org.xander.practice.webapp.vuejs.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionId implements Serializable {
  @JsonView(Views.Id.class)
  private String channelId;
  @JsonView(Views.Id.class)
  private String subscriberId;
}
