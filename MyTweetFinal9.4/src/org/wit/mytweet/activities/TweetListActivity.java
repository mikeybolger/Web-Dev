package org.wit.mytweet.activities;

import org.wit.mytweet.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class TweetListActivity extends FragmentActivity
{

  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_container);

    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
    if (fragment == null)
    {
      fragment = new TweetListFragment();
      manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
    }
  }
}