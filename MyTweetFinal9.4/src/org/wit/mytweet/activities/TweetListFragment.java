package org.wit.mytweet.activities;

import java.util.ArrayList;

import org.wit.android.helpers.IntentHelper;
import org.wit.mytweet.R;
import org.wit.mytweet.app.MyTweetApp;
import org.wit.mytweet.models.Portfolio;
import org.wit.mytweet.models.Tweet;

import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.CheckBox;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.AdapterView.OnItemClickListener;
import android.view.ActionMode;
import android.widget.AbsListView.MultiChoiceModeListener;

public class TweetListFragment extends ListFragment implements OnItemClickListener, MultiChoiceModeListener
{
  private ArrayList<Tweet> tweets;
  private Portfolio portfolio;
  private TweetAdapter adapter;
  private ListView listView;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    getActivity().setTitle(R.string.app_name);

    MyTweetApp app = (MyTweetApp) getActivity().getApplication();
    portfolio = app.portfolio;
    tweets = portfolio.tweets;

    adapter = new TweetAdapter(getActivity(), tweets);
    setListAdapter(adapter);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
  {
    View v = super.onCreateView(inflater, parent, savedInstanceState);
    listView = (ListView) v.findViewById(android.R.id.list);
    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    listView.setMultiChoiceModeListener(this);
    return v;
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id)
  {
    Tweet twe = ((TweetAdapter) getListAdapter()).getItem(position);
    Intent i = new Intent(getActivity(), TweetPagerActivity.class);
    i.putExtra(TweetFragment.EXTRA_TWEET_ID, twe.id);
    startActivityForResult(i, 0);
  }

  @Override
  public void onResume()
  {
    super.onResume();
    ((TweetAdapter) getListAdapter()).notifyDataSetChanged();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
  {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.tweetlist, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId())
    {
    case R.id.menu_item_new_tweet:
      Tweet tweet = new Tweet();
      portfolio.addTweets(tweet);

      Intent i = new Intent(getActivity(), TweetPagerActivity.class);
      i.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
      startActivityForResult(i, 0);
      return true;

    default:
      return super.onOptionsItemSelected(item);
      
      // clear button
    case R.id.action_clear:
      adapter.clear();
      return true;

    case R.id.action_settings:
      //Intent k = new Intent(getActivity(), TweetPagerActivity.class);
      Intent k = new Intent(getActivity(), SettingsActivity.class);
      startActivityForResult(k, 0);
      return true;

    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id)
  {
    Tweet tweet = adapter.getItem(position);
    IntentHelper.startActivityWithData(getActivity(), TweetPagerActivity.class, "TWEET_ID", tweet.id);
  }

  /* ************ MultiChoiceModeListener methods (begin) *********** */
  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu)
  {
    MenuInflater inflater = mode.getMenuInflater();
    inflater.inflate(R.menu.tweet_list_context, menu);
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu)
  {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem item)
  {
    switch (item.getItemId())
    {
    case R.id.menu_item_delete_tweet:
      deleteTweet(mode);
      return true;
    default:
      return false;
    }

  }

  private void deleteTweet(ActionMode mode)
  {
    for (int i = adapter.getCount() - 1; i >= 0; i--)
    {
      if (listView.isItemChecked(i))
      {
        portfolio.deleteTweets(adapter.getItem(i));
      }
    }
    mode.finish();
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onDestroyActionMode(ActionMode mode)
  {
  }

  @Override
  public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)
  {
  }

  /* ************ MultiChoiceModeListener methods (end) *********** */
  class TweetAdapter extends ArrayAdapter<Tweet>
  {
    private Context context;

    public TweetAdapter(Context context, ArrayList<Tweet> residences)
    {
      super(context, 0, residences);
      this.context = context;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      if (convertView == null)
      {
        convertView = inflater.inflate(R.layout.list_item_tweet, null);
      }
      Tweet twe = getItem(position);

      TextView tweet = (TextView) convertView.findViewById(R.id.residence_list_item_geolocation);
      tweet.setText(twe.tweetText);

      TextView dateTextView = (TextView) convertView.findViewById(R.id.residence_list_item_dateTextView);
      dateTextView.setText(twe.getDateString());

      return convertView;
    }
  }
}