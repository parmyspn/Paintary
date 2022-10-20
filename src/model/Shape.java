package model;

import java.awt.*;

public abstract class Shape {
    protected int x;
    protected int y;
    protected int width;
    protected int height;


    private boolean selected;
    private boolean beFilled;
    private Color color;
    private Color fillColor;

    public Shape(Point topLeft ) {
        this((int) topLeft.getX(), (int) topLeft.getY(), 0 , 0);
        color = null;
        selected = false;
        beFilled = false;

    }
    public Shape(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    // getters
    public int getWidth() { return width; }
    public Color getColor(){return color;}

    //set color of the shape
    public void setColor(Color c){
        color = c;
    }

    // EFFECTS: return true iff the given x value is within the bounds of the Shape
    public boolean containsX(int x){
        return (this.x <= x) && (x <= this.x + width);
    }

    // EFFECTS: return true iff the given y value is within the bounds of the Shape
    public boolean containsY(int y) {
        return (this.y <= y) && (y <= this.y + height);
    }

    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Shape
    public boolean contains(Point point) {
        int point_x = point.x;
        int point_y = point.y;

        return containsX(point_x) && containsY(point_y);
    }

    // REQUIRES: the x,y coordinates of the Point are larger than the x,y coordinates of the shape
    // MODIFIES: this
    // EFFECTS:  sets the bottom right corner of this Shape to the given Point
    public void setBounds(Point bottomRight) {
        width  = bottomRight.x - x;
        height = bottomRight.y - y;
    }

    // EFFECTS: draws this Shape on the SimpleDrawingPlayer, if the shape is selected, Shape is filled in
    //          else, Shape is unfilled (white)
    public void draw(Graphics g) {
        //Color save = g.getColor();
        g.setColor(color);
        if(beFilled){
            g.setColor(fillColor);
            fillGraphics(g);
        }
        drawGraphics(g);


    }
    public void setFillColor(Color c){
        fillColor = c;
    }

    public void fill(Graphics g) {
        //Color save = g.getColor();
        g.setColor(fillColor);
        fillGraphics(g);
        drawGraphics(g);


    }

    public void setToBeFilled(){
        beFilled = true;
    }

    // MODIFIES: this
    // EFFECTS:  adds dx to the shapes x coordinate, and dy to the shapes y coordinate.
    //           If the sound associated with the new y-coordinate is different, play the new sound
    public void move(int dx, int dy) {

        x += dx;
        y += dy;

    }




    //EFFECTS: draws the shape
    protected abstract void drawGraphics(Graphics g);
    //EFFECTS: fills the shape
    protected abstract void fillGraphics(Graphics g);





}
