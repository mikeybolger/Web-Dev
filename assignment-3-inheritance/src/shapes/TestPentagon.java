package shapes;

import java.util.ArrayList;

public class TestPentagon
{
    public static void main(String[] args)
    {
        ArrayList<Shapes> shapes = new ArrayList<>();  

        shapes.add(new Pentagon(30, 60, 30, "red"));
        shapes.add(new Pentagon(40, 90, 50, "blue"));
        shapes.add(new Pentagon(50, 120, 70,"green"));
        shapes.add(new Pentagon(60, 150, 90,"black"));    

        for(Shapes shape : shapes)
        {
         shape.makeVisible(); 
         shape.moveTo(0,0);
        }
    }
}
