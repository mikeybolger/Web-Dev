package shapes;

//import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends Shapes
{
    private int xdiameter;
    private int ydiameter;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Ellipse()
    {
        super(20,60,"blue", true);
        this.xdiameter = 60;
        this.ydiameter = 30;
    }

    public Ellipse (int xdiameter, int ydiameter, int xPosition, int yPosition, String color)
    {
        super(xPosition, yPosition, color, true);
        this.xdiameter=xdiameter;
        this.ydiameter=ydiameter;
    }

    /**
     * Change the Ellipse's size by factor 'scale'
     */
    public void changeSize(int scale)
    {
        super.erase();
        xdiameter *= scale;
        ydiameter *= scale;
        draw();
    }
    
    /**
     * Draw the Ellipse with current specifications on screen.
     */
    void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, 
                    xdiameter, ydiameter));
            canvas.wait(10);
        }
    }
}