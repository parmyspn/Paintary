package ui;


import model.Drawing;
import model.Shape;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class DrawingPanel extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;



    private List<Tool> tools;
    private Tool activeTool;
    private Tool activeColor;

    private JPanel shapeArea;
    private JPanel colorArea;
    private JPanel toolArea;


    private Drawing currentDrawing;

    public DrawingPanel() {
        super("Paintry");
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // getters
    public Drawing getCurrentDrawing(){ return currentDrawing; }
    public Color getSelectedColor(){
        if (activeColor!= null){
            ColorTool c = (ColorTool) activeColor;
            return c.getColor();
        }
        return Color.BLACK;

    }


    // MODIFIES: this
    // EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        DrawingMouseListener dml = new DrawingMouseListener();
        addMouseListener(dml);
        addMouseMotionListener(dml);
    }


    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        activeTool = null;
        currentDrawing = null;
        tools = new ArrayList<Tool>();
    }

    // MODIFIES: this
    // EFFECTS:  declares and instantiates a Drawing (newDrawing), and adds it to drawings
    private void addNewDrawing() {
        Drawing newDrawing = new Drawing();
        currentDrawing = newDrawing;
        add(newDrawing, BorderLayout.CENTER);
        validate();
    }

    // MODIFIES: this
    // EFFECTS:  adds given Shape to currentDrawing
    public void addToDrawing(Shape f) {
        currentDrawing.addShape(f);
    }

    // MODIFIES: this
    // EFFECTS:  removes given Shape from currentDrawing
    public void removeFromDrawing(Shape f) {
        currentDrawing.removeShape(f);
    }

    public void fillShapeInDrawing(Shape f) {
        currentDrawing.fillShape(f);
    }



    // EFFECTS: if activeTool != null, then mousePressedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMousePressed(MouseEvent e)  {
        if (activeTool != null)
            activeTool.mousePressedInDrawingArea(e);
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseReleasedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseReleased(MouseEvent e) {
        if (activeTool != null)
            activeTool.mouseReleasedInDrawingArea(e);
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseClicked(MouseEvent e) {
        if (activeTool != null)
            activeTool.mouseClickedInDrawingArea(e);
        repaint();
    }

    // EFFECTS: if activeTool != null, then mouseDraggedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
    private void handleMouseDragged(MouseEvent e) {
        if (activeTool != null)
            activeTool.mouseDraggedInDrawingArea(e);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveTool(Tool aTool) {
        if (activeTool != null)
            activeTool.deactivate();
        aTool.activate();
        activeTool = aTool;
    }

    // MODIFIES: this
    // EFFECTS:  sets the given color as the activeColor
    public void setActiveColor(ColorTool aColor) {
        if (activeColor != null)
            activeColor.deactivate();
        aColor.activate();

        activeColor = aColor;
        currentDrawing.setSelectedColor(aColor.getColor());
    }


    // EFFECTS: return shapes at given point at the currentDrawing
    public Shape getShapeInDrawing(Point point) {
        return currentDrawing.getShapesAtPoint(point);
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {
        toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(1,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.NORTH);

        toolArea.add(createShapeTools());


        MoveTool moveTool = new MoveTool(this, toolArea,"src/data/move.jpg" );
        tools.add(moveTool);

        toolArea.add(shapeArea);

        ResizeTool resizeTool = new ResizeTool(this, toolArea, "src/data/square.jpg");
        tools.add(resizeTool);

        DeleteTool deleteTool = new DeleteTool(this, toolArea, "src/data/square.jpg");
        tools.add(deleteTool);

        FillTool fillTool = new FillTool(this, toolArea,"src/data/move.jpg" );
        tools.add(fillTool);

        createColorTools();



        //setActiveTool(rectTool);
    }
    private JPanel createShapeTools(){
        shapeArea = new JPanel();
        shapeArea.setLayout(new GridLayout(2,4));
        shapeArea.setSize(new Dimension(2, 2));
        ShapeTool rectTool = new RectangleTool(this, shapeArea, "src/data/square.jpg");
        ShapeTool ovalTool = new OvalTool(this, shapeArea, "src/data/oval.jpg");
        ShapeTool lineTool = new LineTool(this, shapeArea, "src/data/line.jpg");
        ShapeTool triangleTool = new TriangleTool(this, shapeArea, "src/data/triangle.jpg");
        tools.add(rectTool);
        tools.add(ovalTool);
        //tools.add(lineTool);
       // tools.add(triangleTool);


        return shapeArea;




    }
    private void createColorTools(){
        colorArea = new JPanel();
        colorArea.setLayout(new GridLayout(1,4));
        colorArea.setSize(new Dimension(1, 1));

        ColorTool redCol = new ColorTool(this , colorArea ,"src/data/square.jpg", Color.RED);
        ColorTool blackCol = new ColorTool(this , colorArea , "src/data/square.jpg",Color.BLACK);
        ColorTool yellowCol = new ColorTool(this , colorArea ,"src/data/square.jpg", Color.YELLOW);
        ColorTool orangeCol = new ColorTool(this , colorArea ,"src/data/square.jpg", Color.ORANGE);
        ColorTool magentaCol = new ColorTool(this , colorArea ,"src/data/square.jpg", Color.MAGENTA);
        ColorTool cyanCol = new ColorTool(this , colorArea , "src/data/square.jpg",Color.CYAN);
        ColorTool lightGreyCol = new ColorTool(this , colorArea ,"src/data/square.jpg", Color.LIGHT_GRAY);
        ColorTool blueCol = new ColorTool(this , colorArea ,"src/data/square.jpg", Color.BLUE);
        toolArea.add(colorArea);

    }

    public static void main(String args[]) {
        new DrawingPanel();
    }

    private class DrawingMouseListener extends MouseAdapter {

        // EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            handleMousePressed(translateEvent(e));
        }

        // EFFECTS: Forward mouse released event to the active tool
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(translateEvent(e));
        }

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(translateEvent(e));
        }

        // EFFECTS:Forward mouse dragged event to the active tool
        public void mouseDragged(MouseEvent e) {
            handleMouseDragged(translateEvent(e));
        }

        // EFFECTS: translates the mouse event to current drawing's coordinate system
        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentDrawing);
        }
    }
}

