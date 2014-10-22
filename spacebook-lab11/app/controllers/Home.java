package controllers;

import play.*;
import play.mvc.*;
import utils.MessageDateComparator;
import utils.MessageFromComparator;

import java.util.*;
import java.util.Date.*;

import models.*;

public class Home extends Controller
{

  public static void index()
  {

    String userId = session.get("logged_in_userid");
    if (userId != null)
    {
      User user = User.findById(Long.parseLong(userId));
      render(user);
    }
    else
    {
      Accounts.index();
    }
  }
  public static void drop(Long id)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    //User user = Accounts.getLoggedInUser();
    
    User friend = User.findById(id);
 
    user.unfriend(friend);
    Logger.info("Dropping " + friend.email);
    index();
  } 
  
 

    public static void byUser()
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
      Collections.sort(user.inbox,new MessageFromComparator());
      
      render(user);
    }
    
    public static void byConversation()
    {  
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));
      
        ArrayList<ArrayList<Message>> conversations = new ArrayList<ArrayList<Message>>();
        
        for(Friendship friendship : user.friendships)
            {
              conversations.add(getConversation(user, friendship.targetUser));
            }
            //byConversation(user, conversations);
            render(user, conversations);
    }
    
    static ArrayList<Message> getConversation (User user, User friend)
    {
      ArrayList<Message> conversation = new ArrayList<Message>();
      
      for(Message message : user.outbox)
      {
        if(message.to == friend)
        {
          conversation.add(message);
        }
      }
      for (Message message : user.inbox)
      {
        if(message.from == friend)
        {
          conversation.add(message);
        }
      }
      Collections.sort(conversation, new MessageDateComparator());
      return conversation;
    }
}