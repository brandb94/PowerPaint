/*
 * TCSS 305 - Power Paint
 * Spring 2014
 */
package actions;

import gui.DrawingArea;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
//import javax.swing.Icon;


//import javax.swing.JFrame;


import tools.RectangleTool;
import tools.Tool;



/**
 * The action used to assign to both rectangle buttons.
 * @author Brandon Bell
 * @version 7 May 2014
 */
@SuppressWarnings("serial")
public class RectangleAction extends AbstractAction {
    
    
    /** The drawing panel for this action. */
    private final DrawingArea myCanvas;
    
    
    /**
     * Instantiates a new rectangle action. Sets the name, 
     * tool, mnemonic, and selected key attributes
     * @param theCanvas the drawing panel this action will affect.

     */
    public RectangleAction(final DrawingArea theCanvas) {
        super("Rectangle");
       
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        myCanvas = theCanvas;

        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent theAction) {
        final Tool rect = new RectangleTool(); 
        myCanvas.setCurrentTool(rect);
    }

}
