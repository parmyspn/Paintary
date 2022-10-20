package model;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Drawing extends JPanel {

    private static final int MUSIC_LINES_SPACE = 30;

	private List<Shape> shapes;
    private Color selectedColor;



	public Drawing() {
		super();
		shapes = new ArrayList<Shape>();
		setBackground(Color.white);
	}

	// getters
    public List<Shape> getShapes() { return this.shapes; }



    // EFFECTS: return true if the given Shape s is contained in Drawing
    public boolean containsShape(Shape s) {
		return shapes.contains(s);
	}

    // EFFECTS: paints grid, playback line, and all figures in drawing
	//          Note to students: calls to repaint gets here via the Java graphics framework
    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Shape shape : shapes) {
            shape.draw(g);

        }
	}



    // MODIFIES: this
    // EFFECTS:  adds the given shape to the drawing
	public void addShape(Shape shape) {
		shapes.add(shape);
	}


    // MODIFIES: this
    // EFFECTS:  removes shape from the drawing
	public void removeShape(Shape shape) {
		shapes.remove(shape);
		repaint();
	}

    public void fillShape(Shape shape){
        shape.setFillColor(selectedColor);
        shape.setToBeFilled();
        repaint();
    }

    public void setSelectedColor(Color c){
        selectedColor = c;

    }

	// EFFECTS: returns the Shape at a given Point in Drawing, if any
	public Shape getShapesAtPoint(Point point) {
		for (Shape shape : shapes) {
			if (shape.contains(point))
				return shape;
		}
		return null;
	}



}
