package org.xander.practice.webapp.vuejs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SUBSCRIPTION")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class UserSubscription {

  @EmbeddedId
  @JsonIgnore
  private UserSubscriptionId id;

  @MapsId("channelId")
  @ManyToOne
  @JsonView(Views.IdName.class)
  @JsonIdentityReference
  @JsonIdentityInfo(
      property = "id",
      generator = ObjectIdGenerators.PropertyGenerator.class
  )
  private User channel;

  @MapsId("subscriberId")
  @ManyToOne
  @JsonView(Views.IdName.class)
  @JsonIdentityReference
  @JsonIdentityInfo(
      property = "id",
      generator = ObjectIdGenerators.PropertyGenerator.class
  )
  private User subscriber;

  @JsonView(Views.IdName.class)
  private Boolean active;

  public UserSubscription(User channel, User subscriber) {
    this.channel = channel;
    this.subscriber = subscriber;
    this.id = new UserSubscriptionId(channel.getId(), subscriber.getId());
  }
}
