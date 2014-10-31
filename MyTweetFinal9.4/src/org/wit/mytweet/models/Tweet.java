package org.wit.mytweet.models;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.wit.mytweet.R;

import android.content.Context;

public class Tweet
{
  public UUID id;

  public String  tweetText;
  public Date    date;
  public boolean tweeted;
  public String  contact;
  public String  email;
  public String  contact_Email;
  public String  contact_Name;
  
  private static final String JSON_ID             = "id"        ;
  private static final String JSON_TWEETTEXT      = "tweetText" ; 
  private static final String JSON_DATE           = "date"      ; 
  private static final String JSON_TWEETED        = "tweeted"   ; 
  private static final String JSON_CONTACT        = "contact"   ;  
  
  public Tweet()
  {
    id = UUID.randomUUID();
    date = new Date();
    //tweetText = "";
  }

  public Tweet(JSONObject json) throws JSONException
  {
    id             = UUID.fromString(json.getString(JSON_ID));
    tweetText      = json.getString(JSON_TWEETTEXT);
    date           = new Date(json.getLong(JSON_DATE));
    tweeted        = json.getBoolean(JSON_TWEETED);
    contact        = json.getString(JSON_CONTACT);

  }

  public JSONObject toJSON() throws JSONException
  {
    JSONObject json = new JSONObject();
    json.put(JSON_ID             , id.toString());  
    json.put(JSON_TWEETTEXT      , tweetText);
    json.put(JSON_DATE           , date.getTime());       
    json.put(JSON_TWEETED        , tweeted);   
    json.put(JSON_CONTACT        , contact);
    return json;
  }

  
  public String getDateString()
  {
    return DateFormat.getDateTimeInstance().format(date);
  }
  
  public String getTweetReport(Context context)
  {
    //String tweetedString = null;
    //if (tweeted)
    //{
      //tweetedString = context.getString(R.string.tweet_report_tweeted);
    //}
    //else
    //{
      //tweetedString = context.getString(R.string.tweet_report_not_tweeted);
    //}
    String dateFormat = "EEE, MMM dd";
    String dateString = android.text.format.DateFormat.format(dateFormat, date).toString();
    String prospectiveContact = contact_Name;
    
    contact_Name = context.getString(R.string.tweet_report_contact, prospectiveContact);
    String message = "Tweet: " + tweetText + "\nDate: " + dateString + "\n" + prospectiveContact;
    return message;
    
   // if (contact == null)
    //{
      //prospectiveTenant = context.getString(R.string.residence_report_nobody_interested);
    //}
    //else
    //{
      //prospectiveTenant = context.getString(R.string.residence_report_prospective_tenant, contact);
    //}
    //String report =  "Location " + tweetText + " Date: " + dateString + " " + tweetedString + " " + prospectiveTenant;
    //return report;
  }
  
  public String getTweetEmail(Context context)
  {
    String tweetEmail = contact_Email;
    tweetEmail = context.getString(R.string.tweet_contact, contact_Email);
    String report = tweetEmail;
    return report;
  }
}