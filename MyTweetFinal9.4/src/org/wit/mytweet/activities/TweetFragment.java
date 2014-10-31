package org.wit.mytweet.activities;

import java.util.UUID;

import org.wit.android.helpers.ContactHelper;
import org.wit.mytweet.R;
import org.wit.mytweet.app.MyTweetApp;
import org.wit.mytweet.models.Portfolio;
import org.wit.mytweet.models.Tweet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import static org.wit.android.helpers.ContactHelper.getDisplayName;
import static org.wit.android.helpers.ContactHelper.getEmail;
import static org.wit.android.helpers.IntentHelper.sendEmail;
import static org.wit.android.helpers.IntentHelper.navigateUp;


public class TweetFragment extends Fragment implements TextWatcher,  
                                                        OnClickListener
                                                        
{
  public static   final String  EXTRA_TWEET_ID = "mytweet.TWEET_ID";

  private static  final int     REQUEST_CONTACT = 1;

  private EditText tweetText;
  
  private Button   emailButton;
  private Button   getContact;
  private Button   tweet_button;
  
  private TextView dateText;

  private Tweet   tweet;
  private Portfolio   portfolio;
  
  private TextView tvCharsRemaining;
  private static final int maxlength = 140;
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    UUID tweId = (UUID)getArguments().getSerializable(EXTRA_TWEET_ID);

    MyTweetApp app = (MyTweetApp) getActivity().getApplication();
    portfolio = app.portfolio; 
    tweet = portfolio.getTweet(tweId);  

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
  {
    View v = inflater.inflate(R.layout.fragment_tweet, parent, false);

   
    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    addListeners(v);
    updateControls(tweet);
    setupTextChangeListener();

    return v;
  } 


  private void addListeners(View v)
  {
    tweetText = (EditText)   v.findViewById(R.id.tweet_text);
    emailButton = (Button)  v.findViewById(R.id.tweet_reportButton);
    getContact = (Button)  v.findViewById(R.id.contact);
    tvCharsRemaining = (TextView) v.findViewById(R.id.CharsRemaining);
    dateText  = (TextView)   v.findViewById(R.id.tweet_date);
    tweet_button = (Button) v.findViewById(R.id.tweet_button);

    tweetText .addTextChangedListener(this);
    emailButton.setOnClickListener(this);
    getContact.setOnClickListener(this);
    tvCharsRemaining.setText(""+maxlength+"" );
    //dateText .setEnabled(false);
    tweet_button.setOnClickListener(this);
  }

  public void updateControls(Tweet tweet)
  {
    tweetText.setText(tweet.tweetText);   
    dateText.setText(tweet.getDateString());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId())
    {
    case android.R.id.home: navigateUp(getActivity());
                            return true;
    }
    return super.onOptionsItemSelected(item);
    
  }

  //If no text is entered in text box do not save tweet
  
  @Override
  public void onPause()
  {
    super.onPause();
    portfolio.saveTweets();
    if (tweetText.getText().toString().equals(""))
    {
      portfolio.deleteTweets(tweet);
    }
  }
     
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data)
   {
     switch (requestCode)
     {
     case REQUEST_CONTACT:
       String email = getEmail(getActivity(), data);
       String name = getDisplayName(getActivity(), data);
       tweet.contact_Name = name;
       tweet.contact_Email = email;
       getContact.setText(email);
       break;
     }
     
   }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after)
  { }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count)
  {}

  @Override
  public void afterTextChanged(Editable c)
  {
    Log.i(this.getClass().getSimpleName(), "tweet text " + c.toString());
    tweet.tweetText = c.toString(); 
    
    int count = 140 - tweetText.length();
    
    if (count == 0)
    {
      Toast.makeText(getActivity(), "Max Characters reached!", Toast.LENGTH_SHORT).show();
    }
  }


  @Override
  public void onClick(View v)
  {
    switch (v.getId())
    {
      case R.id.contact                : Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                                         int count = 140 - tweetText.length();
                                         if (count == 140)
                                         {
                                           Toast.makeText(getActivity(), "Please Enter Message Before Selecting a Contact...", Toast.LENGTH_SHORT).show();
                                         }
                                          else
                                         startActivityForResult(i, REQUEST_CONTACT);
                                         if (tweet.contact != null)
                                         {
                                           getContact.setText("For: "+tweet.contact);
                                         }   
                                         break;
                                         
      case R.id.tweet_reportButton     : count = 140 - tweetText.length();
                                         if (count == 140)
                                         {
                                           Toast.makeText(getActivity(), "Please Enter Message Before Sending e-mail...", Toast.LENGTH_SHORT).show();
                                         }
                                         else
                                         sendEmail(getActivity(), tweet.getTweetEmail(getActivity()), getString(R.string.tweet_report_subject),
                                         tweet.getTweetReport(getActivity()));
                                         break;

      case R.id.tweet_button           : tweetButtonPressed(tweet_button);
                                         break;
    }
  }
  
  public void tweetButtonPressed(View view)
  {
    int count = 140 - tweetText.length(); 
    if (count == 140)
    {
      Toast.makeText(getActivity(), "No Text Entered Tweet Not Sent", Toast.LENGTH_SHORT).show();
    }
    else
    Toast.makeText(getActivity(), "Tweet Sent", Toast.LENGTH_SHORT).show();

  }
  

  private void setupTextChangeListener(){
    tweetText.addTextChangedListener(new TextWatcher(){
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // do nothing
      }
      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        // do nothing
      }
      @Override public void afterTextChanged(Editable s) {
        int count = tweetText.getText().toString().length();
        tvCharsRemaining.setText(""+(maxlength-count));
      }
    });
  }
}