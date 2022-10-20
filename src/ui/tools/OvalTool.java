package ui.tools;

import ui.DrawingPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class OvalTool extends ShapeTool{
    public OvalTool(DrawingPanel editor, JComponent parent, String path) {
        super(editor, parent, path);
        this.filepath = path;
        shape = null;

    }

    public void makeShape(MouseEvent e){
        shape = new model.Oval(e.getPoint());
        shape.setColor(selectedColor);

    }
    protected  String getLabel() {
        return "Oval";
    }
}
