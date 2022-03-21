package org.xander.practice.webapp.vuejs.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @JsonView(Views.FullProfile.class)
  @OneToMany(
      mappedBy = "subscriber",
      orphanRemoval = true
  )
  private Set<UserSubscription> subscriptions = new HashSet<>();

  @JsonView(Views.FullProfile.class)
  @OneToMany(
      mappedBy = "channel",
      orphanRemoval = true,
      cascade = CascadeType.ALL
  )
  private Set<UserSubscription> subscribers = new HashSet<>();
}
