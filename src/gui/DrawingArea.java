/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayDeque;
import java.util.Iterator;
import javax.swing.JPanel;


import tools.PencilTool;
import tools.ShapeHolder;
import tools.Tool;

/**
 * PowerPaint's main drawing area. This is where the magic happens.
 *
 * @author Brandon Bell
 * @version 19 May 2014
 */
@SuppressWarnings("serial")
public class DrawingArea extends JPanel implements PropertyChangeListener {



    /** The panel width. */
    public static final int WIDTH = 500;

    /** The panel Height. */
    public static final int HEIGHT = 300;

    /** The default stroke width. */
    public static final int INITIAL_STROKE = 5;

    /** Constant for the default grid spacing. */
    public static final int SPACING = 10;

    /** The panel background color. */
    public static final Color BACKGROUND_COLOR = Color.WHITE;

    /** The current tool to be used by the panel. */
    private Tool myCurrentTool;

    /** Color of the currently selected shape. */
    private Color myForeGroundColor;
    /** The stroke width. */
    private int myStrokeWidth;

    /** Boolean used to determine whether or not to draw the grid. */
    private boolean myGridEnabled;
    

    
    /** Collection used to store each shape as they are created. */
    private final ArrayDeque<ShapeHolder> myFirst;
    /** Collection used to store the shapes that have been undone. */
    private final ArrayDeque<ShapeHolder> myUndone;


    /**
     * Instantiates a new drawing area.
     */
    public DrawingArea() {
        super();
       
        myFirst = new ArrayDeque<ShapeHolder>();
        myUndone = new ArrayDeque<ShapeHolder>();
        
        myForeGroundColor = Color.BLACK;

        myStrokeWidth = INITIAL_STROKE;

        myCurrentTool = new PencilTool(); //default
        myGridEnabled = false;
        setup();



    }
    
    /**
     * Sets up the components of the drawing panel.
     */
    private void setup() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(BACKGROUND_COLOR);
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseListener());
    }

    /**
     * Paints the current shape.
     * 
     *  @param theGraphics The graphics context to use for painting. 
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2d.setStroke(new BasicStroke(myStrokeWidth));


        // draw all previous shapes
        final Iterator<ShapeHolder> itr = myFirst.descendingIterator();
        while (itr.hasNext()) {
            final ShapeHolder sh = itr.next();
            g2d.setStroke(new BasicStroke(sh.getThickness()));
            g2d.setPaint(sh.getColor());
            g2d.draw(sh.getShape());
            
        }
        
        //draw current shape
        g2d.setStroke(new BasicStroke(myStrokeWidth));

        if (myCurrentTool.getStartPoint() != null 
                        && myCurrentTool.getEndPoint() != null) {
            g2d.setPaint(myForeGroundColor); 

            g2d.draw(myCurrentTool.getShape());
        }
        //draw grid, if selected
        if (myGridEnabled) {
            g2d.setStroke(new BasicStroke(1));
            g2d.setPaint(Color.GRAY);
            drawGrid(g2d);
        }
        
        
    }

    /**
     * Sets the current tool to be used on the panel.
     *
     * @param theTool the new current tool
     */
    public void setCurrentTool(final Tool theTool) {
        myCurrentTool = theTool;
    }


    /**
     * Clears the GUI.
     * NOT CURRENTLY WORKING D:
     */
    public void clear() {
        myFirst.clear();
        myUndone.clear();
        myCurrentTool.setStartPoint(null);
        myCurrentTool.setEndPoint(null);
        
        //tell the undo and redo buttons to disable
        firePropertyChange(ToolBar.UNDO, true, false);
        firePropertyChange(ToolBar.REDO, true, false);
        
        repaint();

    }
    
    /**
     * Undoes the most previous action.
     */
    public void undo() {
        //remove element from first deque
        final ShapeHolder temp = myFirst.pollFirst();
        //add it to second if not null
        if (temp != null) {
            myUndone.push(temp);
            firePropertyChange(ToolBar.REDO, false, true);
        } 
        if (myFirst.isEmpty()) {
            //disable undo button
            firePropertyChange(ToolBar.UNDO, true, false);
            
        }
        repaint();
    }
    
    /**
     * Can only be called after undo has been pressed.
     * Adds the most previously undone shape/stroke back to 
     * the panel. 
     */
    public void redo() {
        //remove element from undone changes
        final ShapeHolder temp = myUndone.pollFirst();
        //add it back to the first deque
        if (temp != null) {
            myFirst.push(temp);
            firePropertyChange(ToolBar.UNDO, false, true);
        }
        if (myUndone.isEmpty()) {
            //disable redo button
            firePropertyChange(ToolBar.REDO, true, false);
        }
        repaint();
      
    }
    

    /**
     * Draws the grid on the panel.
     *
     * @param theGraph the graphics object used to draw the grid.
     */
    public void drawGrid(final Graphics2D theGraph) {
        //Draw horizontal lines
        for (int i = 0; i < getWidth(); i++) {
            theGraph.drawLine(i * SPACING, 0, i * SPACING,
                              getHeight() - 1);
            // draw vertical lines
            for (int j = 0; j < getHeight() / SPACING; j++) {
                theGraph.drawLine(0, i * SPACING, getWidth() - 1, i * SPACING);
            }

        }
    }
    

    /** {@inheritDoc} 
     * Listens to property changes from various action classes
     * to know values to reset.  
     */
    /** 
     * {@inheritDoc}
     * Changes the appropriate value or calls the appropriate message
     * depending on what property change has been fired. 
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("grid".equals(theEvent.getPropertyName())) {
            myGridEnabled = (boolean) theEvent.getNewValue();
            repaint();
        } else if ("color".equalsIgnoreCase(theEvent.getPropertyName())) {
            myForeGroundColor = (Color) theEvent.getNewValue();
        } else if ("thickness".equalsIgnoreCase(theEvent.getPropertyName())) {
            myStrokeWidth = (int) theEvent.getNewValue();
        } else if ("clear".equals(theEvent.getPropertyName())) {
            clear();
        } else if ("callUndo".equalsIgnoreCase(theEvent.getPropertyName())) {
            undo();
        } else if ("callRedo".equalsIgnoreCase(theEvent.getPropertyName())) {
            redo();
        }
        

    }


    //inner class

    /**
     * The listener interface for receiving myMouse events.
     * The class that is interested in processing a myMouse
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's addMyMouseListener method. When
     * the myMouse event occurs, that object's appropriate
     * method is invoked.
     *
     * @see MyMouseEvent
     */
    private class MyMouseListener extends MouseAdapter {


        /** 
         * Event that occurs when the move button is pressed. Sets the start point 
         * of the current tool. 
         * @param theEvent the mouse being pressed
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myCurrentTool.setStartPoint((Point2D) theEvent.getPoint().clone()); 
            myCurrentTool.setEndPoint((Point2D) theEvent.getPoint().clone()); 
  
            repaint();           
        }

        /** 
         * Resets the endpoint of the current tool every time the mouse moves.
         * @param theEvent the mouse being moved
         */
        public void mouseDragged(final MouseEvent theEvent) {
           
            myCurrentTool.setEndPoint((Point2D) theEvent.getPoint().clone()); 
            
            repaint();
            
        }

        /**
         * Occurs when the mouse button is released. Sets the endpoint of the current 
         * tool, adds it to a collection, and repaints the drawing panel. 
         * @param theEvent the mouse button being released.
         */
        public void mouseReleased(final MouseEvent theEvent) {

            myCurrentTool.setEndPoint((Point2D) theEvent.getPoint().clone());
                                      
            final ShapeHolder shape = new ShapeHolder(myCurrentTool.getShape(),
                                                      myForeGroundColor,
                                                      myStrokeWidth);           
            myFirst.push(shape);
            firePropertyChange("undo", false, true);
            
            myCurrentTool.setStartPoint(null);
            myCurrentTool.setEndPoint(null);
            repaint();
            
        }
    }

}


