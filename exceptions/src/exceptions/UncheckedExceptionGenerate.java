package exceptions;

import java.util.Map;

public class UncheckedExceptionGenerate{

    Map<String, Book> books;

    public void triggerNullPointerException()
    {
      books.get(0);
    }
    
}
