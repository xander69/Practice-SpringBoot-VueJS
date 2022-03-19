package org.xander.practice.webapp.vuejs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USERS")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {

  @Id
  @Column(name = "ID")
  @JsonView(Views.IdName.class)
  private String id;
  @Column(name = "NAME")
  @JsonView(Views.IdName.class)
  private String name;
  @Column(name = "USERPIC")
  @JsonView(Views.IdName.class)
  private String userpic;
  @Column(name = "EMAIL")
  @JsonView(Views.FullProfile.class)
  private String email;
  @Column(name = "GENDER")
  @JsonView(Views.FullProfile.class)
  private String gender;
  @Column(name = "LOCALE")
  @JsonView(Views.FullProfile.class)
  private String locale;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "LAST_VISIT")
  @JsonView(Views.FullProfile.class)
  private Date lastVisit;

  @ManyToMany
  @JoinTable(
      name = "USER_SUBSCRIPTIONS",
      joinColumns = @JoinColumn(name = "SUBSCRIBER_ID"),
      inverseJoinColumns = @JoinColumn(name = "CHANNEL_ID")
  )
  @JsonView(Views.FullProfile.class)
  @JsonIdentityReference
  @JsonIdentityInfo(
      property = "id",
      generator = ObjectIdGenerators.PropertyGenerator.class
  )
  private Set<User> subscriptions = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "USER_SUBSCRIPTIONS",
      joinColumns = @JoinColumn(name = "CHANNEL_ID"),
      inverseJoinColumns = @JoinColumn(name = "SUBSCRIBER_ID")
  )
  @JsonView(Views.FullProfile.class)
  @JsonIdentityReference
  @JsonIdentityInfo(
      property = "id",
      generator = ObjectIdGenerators.PropertyGenerator.class
  )
  private Set<User> subscribers = new HashSet<>();
}
