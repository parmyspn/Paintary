package ui.tools;

import ui.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorTool extends Tool{
    private Color color;


    public ColorTool(DrawingPanel editor, JComponent parent ,String path, Color c) {
        color = c;
        this.editor = editor;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  constructs a delete button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent, ImageIcon img) {
        button = new JButton();//add color icon
        button = customizeButton(button);

    }

    protected void createButton(JComponent parent) {
        button = new JButton();//add color icon
        button = customizeButton(button);

    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new ColorTool.ColorToolClickHandler());
    }


    public Color getColor(){
        return color;
    }

    
    protected JButton customizeButton(JButton button) {

        button.setBackground(color);
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }



    private class ColorToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.setActiveColor(ColorTool.this);


        }
    }

}
