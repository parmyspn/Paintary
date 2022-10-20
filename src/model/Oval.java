package model;

import java.awt.*;

public class Oval extends Shape{
    public Oval(Point topLeft){
        super( topLeft);
    }

    //EFFECTS: draws the shape
    @Override
    protected void drawGraphics(Graphics g) {
        g.drawOval(x, y, width, height);
    }

    //EFFECTS: fills the shape
    @Override
    protected void fillGraphics(Graphics g) {
        g.fillOval(x, y, width, height);
    }


}