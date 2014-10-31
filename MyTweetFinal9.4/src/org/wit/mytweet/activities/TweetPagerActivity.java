package org.wit.mytweet.activities;

import java.util.ArrayList;
import java.util.UUID;

import org.wit.mytweet.R;
import org.wit.mytweet.app.MyTweetApp;
import org.wit.mytweet.models.Portfolio;
import org.wit.mytweet.models.Tweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import static org.wit.android.helpers.LogHelpers.info;

public class TweetPagerActivity extends FragmentActivity implements ViewPager.OnPageChangeListener
{
  private ViewPager viewPager;
  private ArrayList<Tweet> tweets;
  private Portfolio portfolio;
  private PagerAdapter pagerAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    viewPager = new ViewPager(this);
    viewPager.setId(R.id.viewPager);
    setContentView(viewPager);
    setTweetList();
    pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tweets);
    viewPager.setAdapter(pagerAdapter);
    viewPager.setOnPageChangeListener(this);
    setCurrentItem();
  }

  private void setTweetList()
  {
    MyTweetApp app = (MyTweetApp) getApplication();
    portfolio = app.portfolio;
    tweets = portfolio.tweets;
  }

  @Override
  public void onPageScrollStateChanged(int arg0)
  {
  }

  @Override
  public void onPageScrolled(int arg0, float arg1, int arg2)
  {
    info(this, "onPageScrolled: arg0 " + arg0 + " arg1 " + arg1 + " arg2 " + arg2);
    Tweet tweet = tweets.get(arg0);
    if (tweet.tweetText != null)
    {
      setTitle(tweet.tweetText);
    }
  }

  @Override
  public void onPageSelected(int arg0)
  {
  }


  private void setCurrentItem()
  {
    UUID res = (UUID) getIntent().getSerializableExtra(TweetFragment.EXTRA_TWEET_ID);
    for (int i = 0; i < tweets.size(); i++)
    {
      if (tweets.get(i).id.toString().equals(res.toString()))
      {
        viewPager.setCurrentItem(i);
        break;
      }
    }
  }

  class PagerAdapter extends FragmentStatePagerAdapter
  {
    private ArrayList<Tweet> tweets;

    public PagerAdapter(FragmentManager fm, ArrayList<Tweet> tweets)
    {
      super(fm);
      this.tweets = tweets;
    }

    @Override
    public int getCount()
    {
      return tweets.size();
    }

    @Override
    public Fragment getItem(int pos)
    {
      Tweet tweet = tweets.get(pos);
      Bundle args = new Bundle();
      args.putSerializable(TweetFragment.EXTRA_TWEET_ID, tweet.id);
      TweetFragment fragment = new TweetFragment();
      fragment.setArguments(args);
      return fragment;
    }
  }
}