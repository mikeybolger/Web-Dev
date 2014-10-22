package models;

import java.util.Date;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Message extends Model
{
  public String messageText;
  //public Date postedAt;
  public Date dateSent;
  
  @ManyToOne
  public User from;

  @ManyToOne
  public User to;

  public Message(User from, User to, String messageText)
  {
    this.from = from;
    this.to = to;
    this.messageText = messageText;
    dateSent = new Date();
  }
}