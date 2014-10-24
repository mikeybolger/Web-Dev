package shapes;

//import java.awt.Rectangle;

public class Rectangle extends Shapes
{
    private int xSideLength;
    private int ySideLength;

    public Rectangle()
    {
      super(60, 50, "red",true); 
      this.xSideLength = 60;
      this.ySideLength = 30;
    }

    public Rectangle(int xSideLength, int ySideLength, int xPosition, int yPosition, String color)
    {
      super(xPosition, yPosition, color, true);
      this.xSideLength = xSideLength;
      this.ySideLength = ySideLength;
    }

    public void setState(int xSideLength, int ySideLength)
    {
        this.xSideLength = xSideLength;
        this.ySideLength = ySideLength;
    }
    
    public void changeSize(int scale)
    {
        if(xSideLength > 0 && ySideLength > 0) 
        {
            super.erase();
            xSideLength *= scale;
            ySideLength *= scale;
            draw();
        }
        else
        {
            System.out.println("Enter positive dimensions");
        }

    }

    void draw()
    {
        if(isVisible) 
        {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
            new java.awt.Rectangle(xPosition, yPosition, xSideLength, ySideLength));
            canvas.wait(10);
        }
    }
}