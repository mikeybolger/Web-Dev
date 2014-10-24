package shapes;

import java.util.ArrayList;

public class TestRectangle
{
  public static void main(String[] args)
  {
      ArrayList<Shapes> shapes = new ArrayList<>();  

       shapes.add(new Rectangle(100, 50, 0, 0, "red"));  

      for(Shapes shape : shapes)
      {
       shape.makeVisible();   
       shape.moveTo(50,100);
      }
  }

}
