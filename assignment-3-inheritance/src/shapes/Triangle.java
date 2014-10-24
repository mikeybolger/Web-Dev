package shapes;
import java.awt.Polygon;

public class Triangle extends Shapes
{
    private int height;
    private int width;

    public Triangle()
    {
        super(50, 15, "green", true);
        isVisible = false;
        setState(30, 40);
    }
    
   public Triangle(int height, int width, int xPosition, int yPosition, String color)
    {      
        super(xPosition, yPosition, color, true);
        setState(height, width);
    }
    
    public void setState(int height, int width)
    { 
        this.height = height;
        this.width = width;
    }
    
    void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }
    
    public void changeSize(int scale)
    {
        erase();
        setState(scale*this.height, scale*this.width);
        draw();
    }
}
