package ui.tools;


import ui.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public abstract class Tool {

    protected JButton button;
    protected DrawingPanel editor;
    protected boolean active;
    protected String filepath;


    public Tool(DrawingPanel editor, JComponent parent, String filepath) {
        this.editor = editor;
        this.filepath = filepath;

        createButton(parent,getImageIcon() );
        addToParent(parent);
        active = false;
        addListener();
    }

    protected Tool() {
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // getters
    public boolean isActive() { return active; }


    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent, ImageIcon img);

    public ImageIcon getImageIcon(){
        ImageIcon imageIcon = new ImageIcon(filepath); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        return imageIcon;
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: default behaviour does nothing
    public void mousePressedInDrawingArea(MouseEvent e) {}

    // EFFECTS: default behaviour does nothing
    public void mouseReleasedInDrawingArea(MouseEvent e) {}

    // EFFECTS: default behaviour does nothing
    public void mouseClickedInDrawingArea(MouseEvent e) {}

    // EFFECTS: default behaviour does nothing
    public void mouseDraggedInDrawingArea(MouseEvent e) {}

}
