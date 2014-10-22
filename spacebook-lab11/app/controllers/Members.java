package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import models.*;

public class Members extends Controller
{
  public static void index()
  {


    
    String userId = session.get("logged_in_userid");
    if (userId != null)
    {  
    List<User> users = User.findAll();
    User user = User.findById(Long.parseLong(userId));
    
    users.remove(user);
    
    render(users);
    }
    else
    {
      Accounts.index();
    }
    
  }
  
  public static void follow(Long id)
  {
    User friend = User.findById(id);
    
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    
    me.befriend(friend);
    Home.index();
  }
}