package shapes;

abstract public class Shapes
{
    int xPosition;
    int yPosition;
    String color;
    boolean isVisible;  
    
    public Shapes(int xPosition, int yPosition, String color, boolean isVisible)
    {
        this.xPosition  = xPosition;
        this.yPosition  = yPosition;
        this.color      = color;
        this.isVisible  = isVisible;         
    }
    
    abstract void draw();
   
    abstract void changeSize(int scale);
    
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Move the triangle to new position whose coordinates in pixels will be (x, y).
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

 
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }



    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }
    
    /**
     * Erase the triangle on screen.
     */
    void erase()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
