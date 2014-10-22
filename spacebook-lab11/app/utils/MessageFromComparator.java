package utils;


import java.util.Comparator;

import models.Message;


public class MessageFromComparator implements Comparator<Message>
{

  @Override
  public int compare(Message o1, Message o2)
  {
    
    String o1Name = o1.from.firstName + o1.from.lastName;
    String o2Name = o2.from.firstName + o2.from.lastName;
    return o1Name.compareTo(o2Name);
  }

}
