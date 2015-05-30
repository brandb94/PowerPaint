/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package gui;

import actions.ColorAction;

import actions.EllipseAction;
import actions.LineAction;
import actions.PencilAction;
import actions.RectangleAction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



/**
 * 
 * @author Brandon Bell
 * @version 19 May 2014
 *
 */
@SuppressWarnings("serial")
public class PowerPaintGUI extends JFrame implements PropertyChangeListener {

    /** A ToolKit, used to obtain the dimensions of the computer's screen. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    /** The panel used to draw on. */
    private final DrawingArea myCanvas;

    /** the currently selected color. */
    private Color myColor;

    /**
     * Instantiates a new power paint gui.
     */
    public PowerPaintGUI() {
        super("TCSS 305 PowerPaint");
        myCanvas = new DrawingArea();
        myColor = Color.BLACK;

    }

    /**
     * Sets the current color of the GUI, called by
     * the ColorAction class.
     *
     * @param theColor the new current color
     */
    public void setCurrentColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Gets the current color selected by the user.
     *
     * @return the current color
     */
    public Color getCurrentColor() {
        return myColor;
    }


    /**
     * runs all necessary methods to put the components together..
     */
    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //The action to be shared by both color buttons.
        final ColorAction colorAct = new ColorAction();

        final MenuBar menuBar = new MenuBar(colorAct);
        
        final ToolBar toolBar = new ToolBar(colorAct, myCanvas);
        
        
        
        final Action[] actions = {new PencilAction(myCanvas),
                                  new LineAction(myCanvas), new RectangleAction(myCanvas), 
                                  new EllipseAction(myCanvas)};

        for (final Action action : actions) {
            // action.addPropertyChangeListener(myCanvas);
            menuBar.createToolButton(action);
            toolBar.createToggleButtons(action);
        }
        toolBar.createUndoRedo();
        
        //assign all propertyChangeListeners
        colorAct.addPropertyChangeListener(myCanvas);
        menuBar.addPropertyChangeListener(myCanvas);
        toolBar.addPropertyChangeListener(myCanvas);
        menuBar.addPropertyChangeListener(this);
        myCanvas.addPropertyChangeListener(toolBar);
        myCanvas.addPropertyChangeListener(menuBar);

        
        //Assign the menu bar and add the toolbar to the frame.
        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.SOUTH);

        setIconImage(new ImageIcon("icons/pp4.png").getImage()); 
        
        add(myCanvas, BorderLayout.CENTER);

        pack();
        setLocation(SCREEN_WIDTH / 2 - getWidth() / 2, SCREEN_HEIGHT / 2 - getHeight() / 2);
        setVisible(true);
    }
    
    /** {@inheritDoc} 
     * Listens for a propertyChange from the close button.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("closeButton".equalsIgnoreCase(theEvent.getPropertyName())) {
            dispatchEvent(new WindowEvent(this,
                                          WindowEvent.WINDOW_CLOSING));
        }

    }

}
