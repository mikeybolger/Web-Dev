package org.wit.mytweet.models;

import java.util.ArrayList;
import java.util.UUID;

import android.util.Log;
import static org.wit.android.helpers.LogHelpers.info;

public class Portfolio
{
  public ArrayList<Tweet> tweets;
  private PortfolioSerializer serializer;

  public Portfolio()
  {
    tweets = new ArrayList<Tweet>();
  }

  public Portfolio(PortfolioSerializer serializer)
  {
    this.serializer = serializer;
    try
    {
      tweets = serializer.loadTweets();
    }
    catch (Exception e)
    {
      info(this, "Error loading tweets: " + e.getMessage());
      tweets = new ArrayList<Tweet>();
    }
  }

  public boolean saveTweets()
  {
    try
    {
      serializer.saveTweets(tweets);
      info(this, "Tweets saved to file");
      return true;
    }
    catch (Exception e)
    {
      info(this, "Error saving Tweets: " + e.getMessage());
      return false;
    }
  }

  public void addTweets(Tweet tweet)
  {
    tweets.add(tweet);
  }

  public void deleteTweets(Tweet c)
  {
    tweets.remove(c);
  }

  public Tweet getTweet(UUID id)
  {
    Log.i(this.getClass().getSimpleName(), "UUID parameter id: " + id);

    for (Tweet twe : tweets)
    {
      if (id.equals(twe.id))
      {
        return twe;
      }
    }
    return null;
  }
  
  public void deleteTweet(Tweet c)
  {
    tweets.remove(c);
  }
}