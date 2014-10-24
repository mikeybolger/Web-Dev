package shapes;

import java.util.ArrayList;

public class TestEllipse
{
  public static void main(String[] args)
  {
      ArrayList<Shapes> shapes = new ArrayList<>();  

      shapes.add(new Ellipse(30, 60, 100, 100, "red"));

      for(Shapes shape : shapes)
      {
          shape.makeVisible();   
      }
  }
}
