package exceptions;

import java.util.Map;

public class CheckedExceptionGenerate
{
  Map<String, Book> books;

  public Book triggerCheckedException(String key) throws BookException
  {
    if(key == null)
    {
      throw new BookException("\nChecked exception demo: key is null");
    }
    return books.get(key);
  }
}
