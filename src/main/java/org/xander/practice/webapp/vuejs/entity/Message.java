package org.xander.practice.webapp.vuejs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @JsonView(Views.Id.class)
  private Long id;

  @Column(name = "TEXT")
  @JsonView(Views.IdName.class)
  private String text;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATE_DT")
  @JsonView(Views.FullMessage.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createDt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CHANGE_DT")
  @JsonView(Views.FullMessage.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date changeDt;

  @Column(name = "LINK", length = 2000)
  @JsonView(Views.FullMessage.class)
  private String link;

  @Column(name = "LINK_TITLE", length = 2000)
  @JsonView(Views.FullMessage.class)
  private String linkTitle;

  @Column(name = "LINK_DESCRIPTION", length = 2000)
  @JsonView(Views.FullMessage.class)
  private String linkDescription;

  @Column(name = "LINK_COVER", length = 2000)
  @JsonView(Views.FullMessage.class)
  private String linkCover;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  @JsonView(Views.FullMessage.class)
  private User author;
}
