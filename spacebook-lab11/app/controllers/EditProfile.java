package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class EditProfile extends Controller
{
  public static void change(String age, String nationality)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    //User user = Accounts.getLoggedInUser();
    //user.firstName = firstName;
    //user.lastName = lastName;
   // user.email = email;
    user.nationality = nationality;
    user.age = age;
    //user.password = password;
    user.save();
    Profile.index();
  }

  
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
  
}