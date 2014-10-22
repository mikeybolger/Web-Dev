package exceptions;

public class Demo
{
  public static void main(String[] args)
  {
    //UncheckedExceptionGenerate demo = new UncheckedExceptionGenerate();
   // demo.triggerNullPointerException();
    
    CheckedExceptionGenerate checked = new CheckedExceptionGenerate();
    try
    {
      checked.triggerCheckedException(null);
    }
    catch (BookException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }    
}
