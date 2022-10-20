package ui.tools;

import ui.DrawingPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TriangleTool extends ShapeTool {
    public TriangleTool(DrawingPanel editor, JComponent parent, String filepath) {
        super(editor, parent, filepath);
        this.filepath = filepath;
        shape = null;

    }

    public void makeShape(MouseEvent e){
        shape = new model.Polygon(e.getPoint());
        shape.setColor(selectedColor);
    }
    protected  String getLabel() {
        return "Rectangle";
    }
}
