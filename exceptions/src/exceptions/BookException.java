package exceptions;

public class BookException extends Exception
{
  String message;

  BookException(String message)
  {
    this.message = message;
  }
}
