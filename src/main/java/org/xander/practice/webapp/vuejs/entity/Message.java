package org.xander.practice.webapp.vuejs.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MESSAGE")
@Getter
@Setter
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "TEXT")
  private String text;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATE_DT")
  private Date createDt;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CHANGE_DT")
  private Date changeDt;

}
