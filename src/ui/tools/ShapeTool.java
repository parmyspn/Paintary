package ui.tools;


import model.Shape;
import ui.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public abstract class ShapeTool extends Tool {
	protected Shape shape;
    public Color selectedColor;




    public ShapeTool(DrawingPanel editor, JComponent parent,String  filepath) {
		super(editor, parent, filepath);

		shape = null;
	}


    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent, ImageIcon img) {

		button = new JButton(img);
		//button = customizeButton(button);

	}

    protected JButton customizeButton(JButton button) {
        return button;
    }




    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
	@Override
	protected void addListener() {
		button.addActionListener(new ShapeToolClickHandler());
	}

	// MODIFIES: this
    // EFFECTS:  a shape is instantiate MouseEvent occurs, and played and
    //           added to the editor's drawing
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
        selectedColor = editor.getSelectedColor();
		makeShape(e);
		shape.setBounds(e.getPoint());
		editor.addToDrawing(shape);
	}


	// MODIFIES: this
    // EFFECTS:  sets the bounds of thes shape to where the mouse is dragged to
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		shape.setBounds(e.getPoint());
	}

	//EFFECTS: Returns the string for the label.
	protected abstract String getLabel() ;



    //EFFECTS: Returns the image for the button.
   // protected abstract String getImage() ;

	//EFFECTS: Constructs and returns the new shape
	abstract void makeShape(MouseEvent e) ;

	private class ShapeToolClickHandler implements ActionListener {

		// EFFECTS: sets active tool to the shape tool
		//          called by the framework when the tool is clicked
    	@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(ShapeTool.this);
		}
	}
}

