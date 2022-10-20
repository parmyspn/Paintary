package model;

import java.awt.*;

public class Rectangle extends Shape{
    public Rectangle(Point topLeft){
        super(topLeft);
    }
    //EFFECTS: draws the shape
    protected  void drawGraphics(Graphics g) {
        g.drawRect(x, y, width, height);
    }

    //EFFECTS: fills the shape
    protected void fillGraphics(Graphics g) {
        g.fillRect(x, y, width, height);
    }

}
