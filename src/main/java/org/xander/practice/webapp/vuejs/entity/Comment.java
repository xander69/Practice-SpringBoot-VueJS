package org.xander.practice.webapp.vuejs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "COMMENT")
@Data
@EqualsAndHashCode(of = {"id"})
public class Comment {

  @Id
  @Column(name = "ID")
  @GeneratedValue
  @JsonView(Views.IdName.class)
  private Long id;

  @Column(name = "COMMENT_TEXT", length = 500)
  @JsonView(Views.IdName.class)
  private String text;

  @Column(name = "CREATE_DT")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonView(Views.IdName.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
  private Date createDt;

  @ManyToOne
  @JoinColumn(name = "MESSAGE_ID")
  @JsonView(Views.FullComment.class)
  private Message message;

  @ManyToOne
  @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
  @JsonView(Views.IdName.class)
  private User author;
}
