package org.xander.practice.webapp.vuejs.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
  private String id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "USERPIC")
  private String userpic;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "GENDER")
  private String gender;
  @Column(name = "LOCALE")
  private String locale;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "LAST_VISIT")
  private Date lastVisit;
}
