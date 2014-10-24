package shapes;

import java.awt.geom.*;

public class Circle extends Ellipse
{
    private int diameter;
    
   /**
    * Create a new circle at default position with default color & diameter.
    */
   public Circle()
   {
        super();
        isVisible = false;
        setState(30);
   }
    
   public Circle (int diameter, int xPosition, int yPosition, String color)
    {
        super(diameter,diameter, xPosition, yPosition, color);
        setState(diameter);
    }
    
    public void setState(int diameter)
    {
        this.diameter = diameter;
    }

    /**
     * Move the circle to new position whose coordinates in pixels will be (x, y).
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void moveTo(int x, int y)
    {
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }
    
    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this circle invisible. If already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }

    /**
     * Move the circle horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int scale)
    {
        erase();
        diameter *= scale;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Ellipse2D.Double(xPosition, yPosition, 
                                                          diameter, diameter));
            canvas.wait(10);
        }
    }

 
}
