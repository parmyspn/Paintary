package ui.tools;

import ui.DrawingPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class LineTool extends ShapeTool{
    public LineTool(DrawingPanel editor, JComponent parent, String path) {
        super(editor, parent, path);
        this.filepath = path;
        shape = null;

    }

    public void makeShape(MouseEvent e){
        shape = new model.Line(e.getPoint());
        shape.setColor(selectedColor);

    }
    protected  String getLabel() {
        return "Line";
    }
}
