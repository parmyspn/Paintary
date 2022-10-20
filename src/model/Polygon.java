package model;

import java.awt.*;

public class Polygon extends Shape{
    public Polygon(Point topLeft) {
        super(topLeft);


    }
    //EFFECTS: draws the shape
    protected  void drawGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4)); // 8-pixel wide pen
        int xs[] ={x- (int) (width/2), x, x+ (int)(width/2)};
        int ys[] ={y-(int)(height/2), y, y+(int)(height/2)};
        g2.drawPolygon(xs, ys,3);

    }

    @Override
    protected void fillGraphics(Graphics g) {

    }


    public void setBounds(Point bottomRight) {
        width  = bottomRight.x ;
        height = bottomRight.y ;
    }

    // MODIFIES: this
    // EFFECTS:  adds dx to the shapes x coordinate, and dy to the shapes y coordinate.
    //           If the sound associated with the new y-coordinate is different, play the new sound
    public void move(int dx, int dy) {

        x += dx;
        y += dy;
        width += dx;
        height += dy;


    }

}
