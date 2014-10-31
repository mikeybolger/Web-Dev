package org.wit.mytweet.app;

import org.wit.mytweet.models.Portfolio;
import org.wit.mytweet.models.PortfolioSerializer;

import android.app.Application;

public class MyTweetApp extends Application
{
  private static final String FILENAME = "portfolio.json";
  public Portfolio portfolio;

  @Override
  public void onCreate()
  {
    super.onCreate();
    PortfolioSerializer serializer = new PortfolioSerializer(this, FILENAME);
    portfolio = new Portfolio(serializer);
  }
}